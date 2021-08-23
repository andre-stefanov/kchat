package com.jambit.kchat.backend

import com.jambit.kchat.model.*
import com.jambit.kchat.model.ChatListEvent.Action.CREATED
import com.jambit.kchat.routes.ChatsRoute
import com.jambit.kchat.routes.MessagesRoute
import com.jambit.kchat.routes.UsersRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.locations.*
import io.ktor.network.sockets.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.websocket.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.util.*
import kotlin.collections.LinkedHashSet

val users = hashMapOf<String, User>()
val chats = hashMapOf<String, Chat>().also {
    val uuid = "0" // general chat
    it[uuid] = Chat(uuid = uuid, title = "General")
}
val messages = mutableListOf<Message>()



// Start application by using Netty engine
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

// Will be used as main entrypoint
@Suppress("unused")
@KtorExperimentalLocationsAPI
fun Application.module() {
    install(CallLogging)
    install(Locations)
    install(ContentNegotiation) { json() }
    install(WebSockets)
    routing {
        trace { application.log.debug(it.buildText()) }

        val wsConnections = Collections.synchronizedSet<WebSocketConnection>(LinkedHashSet())

        webSocket("/") {
            val currentConnection = WebSocketConnection(this)
            wsConnections += currentConnection
            try {
                log.debug("Connected ${currentConnection.name}")

                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val event = Json.decodeFromString<WebSocketEvent>(frame.readText())
                    application.log.debug(event.toString())
                }
            } catch (e: Exception) {
                log.error(e.localizedMessage)
            } finally {
                log.debug("Disconnecting ${currentConnection.name}")
                wsConnections -= currentConnection
            }
        }

        get<UsersRoute> { list ->
            val result = users.values
                .sortedBy { it.name }
                .drop(list.offset)
                .take(list.count)
            call.respond(result)
        }

        get<UsersRoute.ById> {
            val id = call.parameters["id"]
            val user = users[id] ?: run {
                return@get call.respondText(
                    "No user found with id $id",
                    status = HttpStatusCode.NotFound
                )
            }
            call.respond(user)
        }

        location<ChatsRoute> {
            get {
                call.respond(chats.values.toList())
            }
            post {
                val (_, title, participants) = call.receive<Chat>()
                val uuid = UUID.randomUUID().toString()
                val chat = Chat(uuid, title, participants)
                chats[uuid] = chat
                wsConnections.forEach {
                    it.send(ChatListEvent(uuid, CREATED))
                }
                call.respond(HttpStatusCode.Created)
            }
        }

        get<ChatsRoute.ByUUID> { location ->
            val chat = chats[location.uuid] ?: run {
                return@get call.respondText(
                    "No chat found with uuid ${location.uuid}",
                    status = HttpStatusCode.NotFound
                )
            }
            call.respond(chat)
        }

        location<MessagesRoute> {
            get {
                call.respond(messages)
            }
            post {
                messages += call.receive<Message>()
                call.respond(HttpStatusCode.Created)
            }
        }
    }
}