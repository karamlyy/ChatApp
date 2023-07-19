package com.example.realtimechatapp.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.realtimechatapp.firebase.FirebaseManager
import androidx.lifecycle.viewModelScope
import com.example.realtimechatapp.models.AppState

import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val firebaseManager= FirebaseManager.instance
    private val _appState: MutableLiveData<AppState<Unit>?> = MutableLiveData(null)

    val appState: LiveData<AppState<Unit>?> = _appState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                firebaseManager.login(username, password)
                _appState.value = AppState.Success(Unit)
            } catch (t: Throwable) {
                _appState.value = AppState.Error("login unsuccesful")
            }
        }
    }


}