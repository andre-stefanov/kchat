package com.jambit.kchat.backend

import com.jambit.kchat.model.User
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

val users = mutableListOf<User>()

fun Route.userRouting() {
    route("/users") {
        get {
            call.respond(users)
        }
        get("{id}") {
            val id = call.parameters["id"]
            val user = users.find { it.id == id } ?: run {
                return@get call.respondText("No user with id $id", status = HttpStatusCode.NotFound)
            }
            call.respond(user)
        }
        post {

        }
        delete("{id}") {

        }
    }
}

// Start application by using Netty engine
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

// Will be used as main entrypoint
fun Application.module() {
    users.add(User("123", "John"))
    install(ContentNegotiation) {
        json()
        routing {
            userRouting()
        }
    }
}