package com.example.firebaselesson.models

data class Message(
    val sender: String,
    val recevier: String,
    val text: String,
    val timestamp: Long,
    val isRecevied: Boolean = true
)
