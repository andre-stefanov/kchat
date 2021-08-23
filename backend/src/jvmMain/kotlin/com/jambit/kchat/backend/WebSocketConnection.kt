package com.jambit.kchat.backend

import com.jambit.kchat.model.WebSocketEvent
import io.ktor.http.cio.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.atomic.AtomicInteger

class WebSocketConnection(val session: DefaultWebSocketSession) {

    companion object {
        var lastId = AtomicInteger(0)
    }

    val name = "user-${lastId.getAndIncrement()}"

    suspend fun send(event: WebSocketEvent) {
        val frame = Frame.Text(Json.encodeToString(event))
        session.send(frame)
    }
}