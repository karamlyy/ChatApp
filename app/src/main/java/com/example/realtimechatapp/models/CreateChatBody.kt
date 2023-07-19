package com.example.realtimechatapp.models

data class CreateChatBody(
    val users: List<String>,
    val type: ChatType,
    val name: String,
) {
    val hashMap: HashMap<String, Any> get() = hashMapOf(
        "users" to users.sorted(),
        "type" to type.name,
        "name" to name
    )
}

enum class ChatType{
    PRIVATE,
    GROUP
}