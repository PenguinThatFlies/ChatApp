package com.example.firebaselesson.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselesson.R
import com.example.firebaselesson.models.Message
import java.text.SimpleDateFormat
import java.util.*

class ChatRecyclerAdapter: RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>() {

    var items: MutableList<Message> = mutableListOf()
            set(value){
                field = value
                notifyDataSetChanged()
            }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatRecyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position].isRecevied){
            true -> R.layout.item_chat_left
            false -> R.layout.item_chat_right
        }
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ChatRecyclerAdapter.ViewHolder, position: Int) {
        val message = items[position]
        holder.bind(message)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvMessage: TextView = itemView.findViewById(R.id.tvMsg)
        val tvHour: TextView = itemView.findViewById(R.id.tvHour)

        fun bind(message: Message){
            tvMessage.text = message.text
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            tvHour.text = sdf.format(message.timestamp)
        }
    }
}
