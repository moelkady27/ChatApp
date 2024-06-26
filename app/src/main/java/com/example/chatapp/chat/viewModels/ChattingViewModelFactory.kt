package com.example.chatapp.chat.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.chat.db.ChatDatabase

class ChattingViewModelFactory(private val chatDatabase: ChatDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChattingViewModel::class.java)) {
            return ChattingViewModel(chatDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}