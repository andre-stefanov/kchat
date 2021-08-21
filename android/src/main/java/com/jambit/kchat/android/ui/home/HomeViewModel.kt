package com.jambit.kchat.android.ui.home

import androidx.lifecycle.*
import com.jambit.kchat.model.User
import kotlinx.coroutines.flow.flow
import repositories.UserRepository

class HomeViewModel(
    repository: UserRepository
) : ViewModel() {

    val users: LiveData<List<User>> = flow {
        emit(repository.getUsers())
    }.asLiveData(viewModelScope.coroutineContext)

}