package com.example.realtimechatapp.models

data class SignUpBody(
    val email: String,
    val username: String,
    val password: String,
    val profilePhoto: String = "https://picsum.photos/200",
) {
    val hashMap: HashMap<String, Any> get() = hashMapOf(
        "email" to email,
        "username" to username,
        "password" to password,
        "profile_photo" to profilePhoto,
    )
}
