package com.example.realtimechatapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimechatapp.R

class ChatroomRecyclerviewAdapter(
    private val data: List<String>,
    private val images: List<Int>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ChatroomRecyclerviewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: String, image: Int) {
            imageView.setImageResource(image)
            textView.text = item
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_room_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], images[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
