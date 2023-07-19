package com.example.realtimechatapp.models

import com.google.firebase.firestore.DocumentSnapshot

data class UserEntity(
    val id: String,
    val email: String,
    val username: String,
    val profilePhoto: String,
) {
    companion object {
        fun from(document: DocumentSnapshot) = UserEntity(
            id = document.id,
            email = document.get("email") as String,
            username = document.get("username") as String,
            profilePhoto = document.get("profile_photo") as String
        )
    }
}

