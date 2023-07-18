package com.example.realtimechatapp.adapter

import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimechatapp.R

class UsersRecyclerviewAdapter(
    private val data: List<String>,
    private val images: List<Int>,
    private val messages: List<String>,
    private val time: List<String>,

    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<UsersRecyclerviewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profilePhoto: ImageView = itemView.findViewById(R.id.profilePhoto)
        private val username: TextView = itemView.findViewById(R.id.user)
        private val lastMessage: TextView = itemView.findViewById(R.id.last_message)
        private val sentTime: TextView = itemView.findViewById(R.id.last_sent_time)

        fun bind(item: String, image: Int, message: String, time: String) {
            profilePhoto.setImageResource(image)
            username.text = item
            lastMessage.text = message
            sentTime.text = time
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], images[position], messages[position], time[position] )
    }

    override fun getItemCount(): Int {
        return data.size
    }
}