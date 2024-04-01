package com.example.chatapp.chat.models

data class EditMessageResponse(
    val status: String,
    val updatedMessage: UpdatedMessage
)