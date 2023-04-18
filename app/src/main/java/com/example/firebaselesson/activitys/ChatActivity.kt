package com.example.firebaselesson.activitys

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselesson.R
import com.example.firebaselesson.adapters.ChatRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.firebaselesson.models.Message

class ChatActivity : AppCompatActivity() {

    lateinit var fabSendMessage: FloatingActionButton
    lateinit var editMessage: EditText
    lateinit var rvChatList: RecyclerView
    lateinit var chatRecyclerAdapter: ChatRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        fabSendMessage = findViewById(R.id.fabSendMessage)
        editMessage = findViewById(R.id.editMessage)
        rvChatList = findViewById(R.id.rvChatList)
        chatRecyclerAdapter = ChatRecyclerAdapter()

        val messages = mutableListOf(
            Message("Irakli","Antoine","Hello",11465135132,false),
            Message("Antoine", "Irakli","Hello",11465135132,true),
            Message("Irakli","Antoine","Hello",11465135132,false)
        )

        fabSendMessage.setOnClickListener{
            //Message
            val message = editMessage.text.toString().trim()
            if (message.isNotEmpty()){
                messages.add(Message("Irakli", "Antoine", message, System.currentTimeMillis(), false))
                chatRecyclerAdapter.notifyDataSetChanged()
                editMessage.setText("")


                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(editMessage.windowToken, 0)
            }
        }



        chatRecyclerAdapter = ChatRecyclerAdapter()
        chatRecyclerAdapter.items = messages
        rvChatList.apply{
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = chatRecyclerAdapter
        }
    }
}