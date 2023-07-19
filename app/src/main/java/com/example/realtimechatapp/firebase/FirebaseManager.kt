package com.example.realtimechatapp.firebase

import com.example.realtimechatapp.models.ChatType
import com.example.realtimechatapp.models.CreateChatBody
import com.example.realtimechatapp.models.SignUpBody
import com.example.realtimechatapp.models.UserEntity
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseManager {
    companion object {
        val instance: FirebaseManager = FirebaseManager()
    }
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var userEntity: UserEntity? = null
    val user: UserEntity get() = this.userEntity!!

    suspend fun login(username: String, password: String) {
        val document = firestore.collection("user")
            .whereEqualTo("username", username)
            .whereEqualTo("password", password)
            .get()
            .await()
            .first()

        this.userEntity = UserEntity.from(document)
    }

    fun logout() {
        userEntity = null
    }

    suspend fun register(body: SignUpBody): Boolean {
        val users = firestore.collection("user")
            .where(Filter.or(
                Filter.equalTo("email", body.email),
                Filter.equalTo("username", body.username),
            ))
            .get()
            .await()
            .documents
        if (users.isNotEmpty()) {
            return false
        }
        firestore.collection("user")
            .add(body.hashMap)
            .await()
        login(body.username, body.password)
        return true
    }


    suspend fun createChat(body: CreateChatBody): String {
        if (body.type == ChatType.PRIVATE) {
            val chats = firestore.collection("chat")
                .whereEqualTo("users", body.users.sorted())
                .get()
                .await()
                .documents

            if (chats.isNotEmpty()) {
                return chats.first().id
            }
        }
        return firestore.collection("chat")
            .add(body.hashMap)
            .await()
            .id
    }

    suspend fun getUsers(): List<UserEntity>{
        val documents = firestore.collection("user")
            .whereNotEqualTo("username", user.username)
            .get()
            .await()
            .documents
        return documents.map {
            UserEntity.from(it)
        }
    }
}