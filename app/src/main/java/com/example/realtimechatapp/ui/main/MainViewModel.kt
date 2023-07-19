package com.example.realtimechatapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechatapp.firebase.FirebaseManager
import com.example.realtimechatapp.models.AppState
import com.example.realtimechatapp.models.ChatType
import com.example.realtimechatapp.models.CreateChatBody
import com.example.realtimechatapp.models.SignUpBody
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val firebaseManager= FirebaseManager.instance
    private val _appState: MutableLiveData<AppState<String>?> = MutableLiveData(null)

    val appState: LiveData<AppState<String>?> = _appState

    fun createChat(users: List<String>, type: ChatType, name: String) {
        viewModelScope.launch {
            try {
                val chatID = firebaseManager.createChat(
                    CreateChatBody(
                        users = users,
                        type = type,
                        name = name,
                    )
                )
                _appState.value = AppState.Success(chatID)
            } catch (t: Throwable) {
                _appState.value = AppState.Error("login error")
            }
        }
    }
}