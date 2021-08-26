package com.jambit.kchat.android.ui.chatlist

import android.app.Application
import androidx.lifecycle.*
import com.jambit.kchat.android.utils.getStringLiveData
import com.jambit.kchat.android.utils.preferences
import repositories.ChatRepository

class ChatListViewModel(
    application: Application,
    chatRepository: ChatRepository
) : AndroidViewModel(application) {

    val chats = chatRepository.getChats().asLiveData(viewModelScope.coroutineContext)

    val username: LiveData<String> = getApplication<Application>()
        .preferences()
        .getStringLiveData("username", "John")
}