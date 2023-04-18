package com.example.firebaselesson.activitys

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaselesson.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity: AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordBinding

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.forgotPasswordSendLinkButton.setOnClickListener {

            val email = binding.forgotPasswordEmailEditText.text.toString().trim()

            if (email.isEmpty()){
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "link sent", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}