package com.example.chatapp.chat.models

data class ChatListUsersResponse(
    val chatUsers: List<ChatUser>,
    val status: String
)