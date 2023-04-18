package com.example.firebaselesson.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselesson.R
import com.example.firebaselesson.adapters.UserRecyclerAdapter
import com.example.firebaselesson.models.User

class UsersSearchActivity : AppCompatActivity() {

    lateinit var rvUsers: RecyclerView
    lateinit var editSearch: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_search)

        rvUsers = findViewById(R.id.rvUsers)
        editSearch = findViewById(R.id.editSearch)

        val users = mutableListOf(
            User("","pingvina47@gmail.com", "Pingivn Pingivnovich", ""),
            User("","iraklisharikadze616@gmail.com", "Irakli Shariakdze", ""),
            User("", "alikazebrovna248@gmail.com", "Alika Zebrovich", "")
        )

        val userRecyclerAdapter = UserRecyclerAdapter()
        //userRecyclerAdapter.items = users
        rvUsers.apply {
            layoutManager = LinearLayoutManager(this@UsersSearchActivity)
            adapter = userRecyclerAdapter
        }

        userRecyclerAdapter.items = users

        editSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               //asas
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userRecyclerAdapter.filter.filter(s.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                //asdasd
            }

        })


    }
}