package com.jambit.kchat.routes

import io.ktor.locations.*

@Location("/users")
data class UsersRoute(val offset: Int = 0, val count: Int = 10) {

    @Location("/{id}")
    data class ById(val id: String)

}

@Location("/messages")
class MessagesRoute

@Location("/chats")
data class ChatsRoute(val offset: Int = 0, val count: Int = 10) {

    @Location("/{uuid}")
    data class ByUUID(val uuid: String)

}