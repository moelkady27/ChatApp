package com.example.chatapp.chat.models

import com.example.chatapp.chat.models.Chat

data class GetConversationResponse(
    val chat: Chat,
    val status: String
)