package com.example.realtimechatapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimechatapp.R
import com.example.realtimechatapp.adapter.ChatroomRecyclerviewAdapter
import com.example.realtimechatapp.adapter.UsersRecyclerviewAdapter
import com.example.realtimechatapp.firebase.FirebaseManager
import com.example.realtimechatapp.models.AppState
import com.example.realtimechatapp.models.ChatType
import com.example.realtimechatapp.ui.ChatroomActivity
import com.example.realtimechatapp.ui.PrivateChatActivity
import com.example.realtimechatapp.ui.signin.SignInViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewChatrooms: RecyclerView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerViewChatrooms = findViewById(R.id.recyclerViewChatrooms)
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers)


        recyclerViewChatrooms.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val dataChatrooms = listOf("Karam", "Jabrayil", "Ennaghi", "Isa", "Karam", "Jabrayil", "Ennaghi", "Isa") // Replace with your actual data
        val imagesChatrooms = listOf(R.drawable.chatroom1, R.drawable.chatroom2, R.drawable.chatroom3, R.drawable.chatroom4, R.drawable.chatroom1, R.drawable.chatroom2, R.drawable.chatroom3, R.drawable.chatroom4) // Replace with your actual image resources
        val adapterChatrooms = ChatroomRecyclerviewAdapter(dataChatrooms, imagesChatrooms) { selectedItem ->
            val selectedIndex = dataChatrooms.indexOf(selectedItem)
            val selectedImage = imagesChatrooms[selectedIndex]

            val intent = Intent(this, ChatroomActivity::class.java)
            intent.putExtra("selectedItem", selectedItem)
            intent.putExtra("selectedImage", selectedImage)
            startActivity(intent)
        }
        recyclerViewChatrooms.adapter = adapterChatrooms

        recyclerViewUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //User Recyclerview data
        val dataUsers = listOf("Maciej Kowalski", "Odeusz Piotrowski", "Bożenka Malina", "Maciej Orłowski", "Krysia Eurydyka", "MC Bastek", "Maciej Kowalski", "Odeusz Piotrowski", "Bożenka Malina", "Maciej Orłowski", "Krysia Eurydyka", "MC Bastek") // Replace with your actual data
        val imagesUsers = listOf(R.drawable.profile1, R.drawable.profile2, R.drawable.profile3, R.drawable.profile4, R.drawable.profile5, R.drawable.profile6, R.drawable.profile1, R.drawable.profile2, R.drawable.profile3, R.drawable.profile4, R.drawable.profile5, R.drawable.profile6) // Replace with your actual image resources
        val lastMessagesUsers = listOf("How are you", "Will do, super, thank you", "Uploaded file.", "Here is another tutorial, if you...", "\uD83D\uDE02", "no pracujemy z domu przez 5 ...", "How are you", "Will do, super, thank you", "Uploaded file.", "Here is another tutorial, if you...", "\uD83D\uDE02", "no pracujemy z domu przez 5 ..." )
        val lastSentTime = listOf("8:43", "Tue", "Sun", "23 Mar", "18 Mar", "01 Feb", "8:43", "Tue", "Sun", "23 Mar", "18 Mar", "01 Feb")

        val adapterUsers = UsersRecyclerviewAdapter(dataUsers, imagesUsers, lastMessagesUsers, lastSentTime ) { selectedItem ->
            val selectedIndexUser = dataUsers.indexOf(selectedItem)
            val selectedImage = imagesUsers[selectedIndexUser]
            val selectedIndexMessage = dataUsers.indexOf(selectedItem)
            val selectedIndexTime = dataUsers.indexOf(selectedItem)

            val intent = Intent(this, PrivateChatActivity::class.java)
            intent.putExtra("selectedItem", selectedItem)
            intent.putExtra("selectedImage", selectedImage)
            intent.putExtra("selectedItem", selectedIndexMessage)
            intent.putExtra("selectedItem", selectedIndexTime)

            startActivity(intent)
        }

        recyclerViewUsers.adapter = adapterUsers

        viewModel.appState.observe(this, Observer {
            if (it == null) return@Observer
            when(it){
                is AppState.Success ->
                    Toast.makeText(this@MainActivity, it.data, Toast.LENGTH_SHORT).show()
                is AppState.Error ->
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
            }
        })
        val createChatButton = findViewById<ImageView>(R.id.createChat)

        createChatButton.setOnClickListener {
            viewModel.createChat(
                users = listOf(FirebaseManager.instance.user.id,"EyPJVG6Zn9K11YUZqC9K"),
                type = ChatType.PRIVATE,
                name = "Rza"
            )
        }

    }
}