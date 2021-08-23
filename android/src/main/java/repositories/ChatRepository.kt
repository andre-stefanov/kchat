package repositories

import com.jambit.kchat.android.api.RestClient
import com.jambit.kchat.android.api.WebSocketClient
import com.jambit.kchat.model.Chat
import com.jambit.kchat.model.ChatListEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class ChatRepository(
    private val restClient: RestClient,
    private val webSocketClient: WebSocketClient
) {

    @ExperimentalCoroutinesApi
    fun getChats() : Flow<List<Chat>> = webSocketClient.events()
        .filterIsInstance<ChatListEvent>()
        .map { listOf(restClient.getChat(it.chatUuid)) }
        .onStart { emit(restClient.getChats()) }
        .runningReduce { list, chat -> list + chat }

}