package com.example.realtimechatapp.ui.userselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimechatapp.firebase.FirebaseManager
import com.example.realtimechatapp.models.AppState
import com.example.realtimechatapp.models.UserEntity
import kotlinx.coroutines.launch

class UserSelectionViewModel : ViewModel() {
    private val firebaseManager= FirebaseManager.instance
    private val _appState: MutableLiveData<AppState<List<UserEntity>>?> = MutableLiveData(null)

    val appState: LiveData<AppState<List<UserEntity>>?> = _appState

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                _appState.value = AppState.Success(firebaseManager.getUsers())
            }catch (t:Throwable){
                _appState.value = AppState.Error("error")
            }
        }

    }
}