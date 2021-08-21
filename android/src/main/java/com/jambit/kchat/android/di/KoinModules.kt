package com.jambit.kchat.android.di

import com.jambit.kchat.android.rest.RestClient
import com.jambit.kchat.android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import repositories.UserRepository

val mainModule = module {

    // network
    single { RestClient() }

    // repositories
    single { UserRepository(get()) }

    // view models
    viewModel { HomeViewModel(get()) }
}