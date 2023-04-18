package com.example.firebaselesson.models

data class User(
    var uuid: String,
    val email: String,
    val fullName: String,
    val image: String
) { constructor(): this("","","","")}
