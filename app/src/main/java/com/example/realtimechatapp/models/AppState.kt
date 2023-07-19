package com.example.realtimechatapp.models

sealed class AppState<T> {
    data class Error<T>(val message: String): AppState<T>()
    data class Success<T>(val data: T): AppState<T>()
}
