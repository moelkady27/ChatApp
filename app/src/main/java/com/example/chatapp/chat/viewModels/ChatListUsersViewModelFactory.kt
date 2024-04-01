package com.example.chatapp.chat.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.chat.db.ChatUsersDatabase

class ChatListUsersViewModelFactory(
    private val chatUsersDatabase: ChatUsersDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatListUsersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatListUsersViewModel(chatUsersDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}