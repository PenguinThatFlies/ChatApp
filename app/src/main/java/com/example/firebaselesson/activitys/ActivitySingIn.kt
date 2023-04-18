package com.example.firebaselesson.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaselesson.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class ActivitySingIn:AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private val auth = FirebaseAuth.getInstance()

    private fun init(){
        binding.apply {

            loginButton.setOnClickListener {

                val email = loginEmailEditText.text.toString().trim()
                val password = loginPasswordEditText.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()){
                    return@setOnClickListener
                }

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        goToProfile()
                    } else {
                        Toast.makeText(this@ActivitySingIn, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            loginForgotPasswordButton.setOnClickListener {
                startActivity(Intent(this@ActivitySingIn, ForgotPasswordActivity::class.java))
            }

            loginNotRegisteredButton.setOnClickListener {
                startActivity(Intent(this@ActivitySingIn, ActivitySignUp::class.java))
                finish()
            }
        }

    }

    fun goToProfile(){
        startActivity(Intent(this@ActivitySingIn, MainActivity::class.java))
    }
}