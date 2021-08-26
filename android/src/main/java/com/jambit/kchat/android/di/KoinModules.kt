package com.jambit.kchat.android.di

import com.jambit.kchat.android.api.RestClient
import com.jambit.kchat.android.api.WebSocketClient
import com.jambit.kchat.android.ui.chat.ChatViewModel
import com.jambit.kchat.android.ui.chatlist.ChatListViewModel
import com.jambit.kchat.android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import repositories.MessageRepository
import repositories.UserRepository
import repositories.ChatRepository

val mainModule = module {

    // network
    single { RestClient() }
    single { WebSocketClient() }

    // repositories
    single { UserRepository(get()) }
    single { MessageRepository(get(), get()) }
    single { ChatRepository(get(), get()) }

    // view models
    viewModel { HomeViewModel(get()) }
    viewModel { ChatListViewModel(get(), get()) }
    viewModel { ChatViewModel() }
}