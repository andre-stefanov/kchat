package com.jambit.kchat.android.ui.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import repositories.ChatRepository

class ChatListViewModel(
    chatRepository: ChatRepository
) : ViewModel() {

    val chats = chatRepository.getChats().asLiveData(viewModelScope.coroutineContext)

}