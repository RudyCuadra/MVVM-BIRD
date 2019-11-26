package com.example.advancedandroidcourse.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedandroidcourse.R
import com.example.advancedandroidcourse.model.Request
import com.example.advancedandroidcourse.repository.UserRepository
import com.example.advancedandroidcourse.ui.Event
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel(){

    private val _uiState =
        MutableLiveData<UserDataState>()

    val uiState: LiveData<UserDataState>
    get() = _uiState

    fun retrieveUsers(){
        viewModelScope.launch{
            runCatching{
                //Se inicia la peticion
                emitUiState(showProgress = true)
                userRepository.obtainUsers()
            }.onSuccess{
                //SE OBTUVO EL JSON CON EXITO
                emitUiState(user = Event(it))
            }.onFailure{
                println("Fallo en: $it")
                emitUiState(error = Event(R.string.internet_failure_error))
            }
        }
    }

    fun emitUiState(
        showProgress: Boolean = false,
        user: Event<Request>? = null,
        error: Event<Int>? = null){
        val dataState = UserDataState(
            showProgress,
            user,
            error
        )
        _uiState.value = dataState
    }

    data class UserDataState(
        val showProgress: Boolean,
        val user: Event<Request>?,
        val error: Event<Int>?)
}