package com.example.firebaselesson.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselesson.R
import com.example.firebaselesson.activitys.ChatActivity
import com.example.firebaselesson.models.Friend
import com.google.android.material.imageview.ShapeableImageView
import java.text.SimpleDateFormat
import java.util.*

class FriendsRecyclerAdapter: RecyclerView.Adapter<FriendsRecyclerAdapter.ViewHolder>() {

    var items: MutableList<Friend> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsRecyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FriendsRecyclerAdapter.ViewHolder, position: Int) {
        val friend = items[position]
        holder.bind(friend)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFriend : ShapeableImageView = itemView.findViewById(R.id.tvFriend)
        val tvName : TextView = itemView.findViewById(R.id.tvName)
        val tvLastMsg : TextView = itemView.findViewById(R.id.tvLastMsg)
        val tvHour : TextView = itemView.findViewById(R.id.tvHour)

        fun bind(friend: Friend){
            tvName.text = friend.name
            tvLastMsg.text = friend.lastMsg
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            tvHour.text = sdf.format(friend.timestamp)

            itemView.setOnClickListener{
                Intent(itemView.context, ChatActivity::class.java).also {
                    itemView.context.startActivity(it)
                }
            }
        }


    }
}
