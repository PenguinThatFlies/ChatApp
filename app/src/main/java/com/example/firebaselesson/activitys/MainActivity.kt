package com.example.firebaselesson.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselesson.R
import com.example.firebaselesson.adapters.FriendsRecyclerAdapter
import com.example.firebaselesson.models.Friend
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var rvFriends: RecyclerView
    lateinit var fabChat: FloatingActionButton
    lateinit var profile: FloatingActionButton
    lateinit var settings: FloatingActionButton

    lateinit var friendsRecyclearAdapter: FriendsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFriends = findViewById(R.id.rvFriends)
        fabChat = findViewById(R.id.fabChat)
        profile = findViewById(R.id.profile)
        settings = findViewById(R.id.settings)

        settings.setOnClickListener{
            Intent(this, ActivityMenu::class.java).also{
                startActivity(it)
            }
        }

        fabChat.setOnClickListener{
            Intent(this, UsersSearchActivity::class.java).also{
                startActivity(it)
            }
        }

        profile.setOnClickListener{
            Intent(this, ProfileActivity::class.java).also{
                startActivity(it)
            }
        }

        val friends = mutableListOf(
            Friend("Irakli", "Cv?", "", 1235468745),
            Friend("Antoine", "Hello", "", 1235468745),
            Friend("Levante", "How Are u?", "", 1235468745)
        )

        friendsRecyclearAdapter = FriendsRecyclerAdapter()
        friendsRecyclearAdapter.items = friends
        rvFriends.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = friendsRecyclearAdapter
        }

//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.Logout){
            val auth = Firebase.auth
            auth.signOut()
            Intent(this, ActivitySingIn::class.java).also{
                startActivity(it)
            }
            finish()
        }

        if(item.itemId == R.id.profile){
            Intent(this, ProfileActivity::class.java).also{
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}