package repositories

import com.jambit.kchat.android.api.RestClient
import com.jambit.kchat.android.api.WebSocketClient

class MessageRepository(
    private val restClient: RestClient,
    private val wsClient: WebSocketClient
) {
}