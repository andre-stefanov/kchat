package com.jambit.kchat.model

import kotlinx.serialization.Serializable

@Serializable
sealed class WebSocketEvent

@Serializable
data class ChatListEvent(
    val chatUuid: String,
    val action: Action
) : WebSocketEvent() {
    enum class Action {
        CREATED,
        REMOVED,
        UPDATED
    }
}
