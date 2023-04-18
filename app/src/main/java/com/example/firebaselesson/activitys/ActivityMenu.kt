package com.example.firebaselesson.activitys

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaselesson.R
import com.example.firebaselesson.databinding.ActivityMenuBinding
import java.net.Authenticator

class ActivityMenu: AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.UpdateTextView.setOnClickListener{
            startActivity(Intent(this, UpdatePasswordActivity::class.java))
            finish()
        }

        binding.Logout.setOnClickListener{
            startActivity(Intent(this, ActivitySingIn::class.java))
            finish()
        }
    }


}