package com.example.firebaselesson.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaselesson.R
import com.example.firebaselesson.databinding.ActivitySignUpBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.net.PasswordAuthentication

class ActivitySignUp: AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var SignUpFullname : EditText
    private lateinit var signUpEmailEditText: EditText
    private lateinit var signUpPassword: EditText
    lateinit var SignUpConfirmPassword : EditText
    private lateinit var signUpButton: Button
    private lateinit var signUpAlreadyRegisteredButton: TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        listeners()
    }

    private fun listeners() {
        signUpButton.setOnClickListener {

            val fullname = SignUpFullname.text.toString().trim()
            val email = signUpEmailEditText.text.toString().trim()
            val password = signUpPassword.text.toString().trim()
            val confirm_password = SignUpConfirmPassword.text.toString().trim()

            if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirm_password.isEmpty() || password.length < 5 || password.contains(' ')){

                if(fullname.isEmpty()){
                    SignUpFullname.error = "შეიყვანეთ სახელი"
                }
                if(email.isEmpty()){
                    signUpEmailEditText.error = "შეიყვანეთ სახელი"
                }
                if(password.isEmpty()){
                    signUpPassword.error = "შეიყვანეთ პაროლი"
                }
                if(confirm_password.isEmpty()){
                    SignUpConfirmPassword.error = "შეიყვანეთ განმეორებითი პაროლი"
                }

                Toast.makeText(this, "დაფიქსირდა შეცდომა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                if(password != confirm_password){
                    SignUpConfirmPassword.error = "პაროლი არ ემთხვევა"
                } else {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                        if(task.isSuccessful){


                            val user = hashMapOf(
                                "fullname" to fullname,
                                "email" to email
                            )

                            val currentUser = auth.currentUser
                            // data base firebase
                            val db = Firebase.firestore
                            db.collection("users").document(currentUser!!.uid).set(user).addOnCompleteListener {
                                Intent(this, MainActivity::class.java).also {
                                    startActivity(it)
                                }
                            }.addOnFailureListener{
                                SignUpConfirmPassword.error = "დაფისქსირდა შეცდომა, გთხოვთ ცადოთ თავიდან"
                            }
                        } else {
                            SignUpConfirmPassword.error = "დაფისქსირდა შეცდომა, გთხოვთ ცადოთ თავიდან"
                        }
                    }
                }
            }

//            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
//                if (task.isSuccessful){
//
//
//                    startActivity(Intent(this, ActivitySingIn::class.java))
//                    finish()
//                } else {
//                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                }
//            }
        }

        signUpAlreadyRegisteredButton.setOnClickListener {
            startActivity(Intent(this, ActivitySingIn::class.java))
            finish()
        }
    }

    private fun init(){
        SignUpFullname = findViewById(R.id.SignUpFullname)
        signUpEmailEditText = findViewById(R.id.signUpEmailEditText)
        signUpPassword = findViewById(R.id.SignUpPassword)
        SignUpConfirmPassword = findViewById(R.id.SignUpConfirmPassword)
        signUpButton = findViewById(R.id.signUpButton)
        signUpAlreadyRegisteredButton = findViewById(R.id.signUpAlreadyRegisteredButton)
    }



}