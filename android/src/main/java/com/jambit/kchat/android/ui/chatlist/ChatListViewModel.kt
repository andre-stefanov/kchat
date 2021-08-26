package com.jambit.kchat.android.ui.chatlist

import android.app.Application
import androidx.lifecycle.*
import repositories.ChatRepository

class ChatListViewModel(
    application: Application,
    chatRepository: ChatRepository
) : AndroidViewModel(application) {

    val chats = chatRepository.getChats().asLiveData(viewModelScope.coroutineContext)

}