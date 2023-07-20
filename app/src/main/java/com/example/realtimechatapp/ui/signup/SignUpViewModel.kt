package com.example.realtimechatapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechatapp.firebase.FirebaseManager
import com.example.realtimechatapp.models.AppState
import com.example.realtimechatapp.models.SignUpBody
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val firebaseManager= FirebaseManager.instance
    private val _appState: MutableLiveData<AppState<Unit>?> = MutableLiveData(null)

    val appState: LiveData<AppState<Unit>?> = _appState

    fun signup(email: String, username: String, password: String, profilePhoto: String) {
        viewModelScope.launch {
            try {
                val success = firebaseManager.register(
                   SignUpBody(
                       email = email,
                       username = username,
                       password = password,
                       profilePhoto = profilePhoto,

                   )
                )
                if(success) {
                    _appState.value = AppState.Success(Unit)
                }
                else {
                    _appState.value = AppState.Error("already taken user")
                }
            } catch (t: Throwable) {
                _appState.value = AppState.Error("error while registration")
            }
        }
    }
}