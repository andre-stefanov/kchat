package com.jambit.kchat.android.api

import android.util.Log
import com.jambit.kchat.model.WebSocketEvent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val TAG = "WebSocketClient"

class WebSocketClient {

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }

    @ExperimentalCoroutinesApi
    private inline fun <reified T> connect(path: String) = callbackFlow {
        client.webSocket(
            method = HttpMethod.Get,
            host = "10.0.21.203",
            port = 8080,
            path = path
        ) {
            while (isActive) {
                val frame = incoming.receive() as? Frame.Text ?: continue
                val message = Json.decodeFromString<T>(frame.readText())
                trySend(message)
            }
        }

        Log.d(TAG, "stopped listening to the socket")

        awaitClose {
            Log.d(TAG, "closing socket")
            client.close()
        }
    }

//    @ExperimentalCoroutinesApi
//    fun messages() = connect<Message>("/messages")

    @ExperimentalCoroutinesApi
    fun events() = connect<WebSocketEvent>("/")
}