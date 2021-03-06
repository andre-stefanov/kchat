package com.jambit.kchat.android.api

import android.util.Log
import com.jambit.kchat.android.BuildConfig
import com.jambit.kchat.model.Chat
import com.jambit.kchat.model.User
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class RestClient {

    private val client = HttpClient(Android) {

        // setup json by using kotlin serialization
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
            })
        }

        // Redirect Ktor logging to Android LogCat
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Ktor", message)
                }
            }
            level = LogLevel.ALL
        }

        // define defaults for all requests
        install(DefaultRequest) {
            host = BuildConfig.API_HOST
            port = 8080
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    suspend fun getUsers(): List<User> = client.get("/users")

    suspend fun getChats(): List<Chat> = client.get("/chats")

    suspend fun getChat(uuid: String): Chat = client.get("/chats/$uuid")
}