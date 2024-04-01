package com.example.chatapp.chat.models

data class UpdatedMessage(
    val _id: String,
    val createdAt: String,
    val media: List<Any>,
    val messageContent: String,
    val receiverId: String,
    val senderId: String,
    val updatedAt: String
)