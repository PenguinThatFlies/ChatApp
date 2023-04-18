package com.example.firebaselesson.activitys

import android.app.Instrumentation.ActivityResult
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaselesson.R
import com.example.firebaselesson.models.User
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.util.UUID

class ProfileActivity:AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var currentUser: FirebaseUser? = null

    lateinit var ivUser: ShapeableImageView
    private lateinit var FullnameEditText : EditText
    private lateinit var EmailEditText: EditText
    lateinit var btnSave: Button

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ivUser = findViewById(R.id.ivUser)
        FullnameEditText = findViewById(R.id.FullnameEditText)
        EmailEditText = findViewById(R.id.EmailEditText)
        btnSave = findViewById(R.id.btnSave)

        auth = Firebase.auth
        db = Firebase.firestore
        currentUser = auth.currentUser

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        val docRef = db.collection("users").document(uid!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val fullnm = document.data!!["fullname"].toString()
                    val eml = document.data!!["email"].toString()

                    FullnameEditText.hint = fullnm
                    EmailEditText.hint = eml

                    val user = document.toObject(User::class.java)
                    user?.let{
                        user.uuid = currentUser!!.uid
                        setUserData(user)
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }




        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            it?.let{
                ivUser.setImageURI(it)
            }
        }

        ivUser.setOnClickListener{
            pickImage.launch("image/*")
        }
    }

    private fun setUserData(user: User) {
        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("images/${UUID.randomUUID()}")

        val bitmap = (ivUser.drawable as BitmapDrawable).bitmap

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

//        val uploadTask = imageRef.putBytes(data)
//        uploadTask.addOnCanceledListener { taskSnapShot ->
//            imageRef.downloadUrl.addOnCompleteListener { uri ->
//                user.image = uri.toString()
//            }
//        }
    }
}

