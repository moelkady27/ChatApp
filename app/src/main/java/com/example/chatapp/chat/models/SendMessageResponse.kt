package com.example.chatapp.chat.models

import com.example.chatapp.chat.models.MessageChatting

data class SendMessageResponse(
    val message: MessageChatting,
    val status: String
)