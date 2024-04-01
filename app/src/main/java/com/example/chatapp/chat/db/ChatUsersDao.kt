package com.example.chatapp.chat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chatapp.chat.models.ChatUser

@Dao
interface ChatUsersDao {
    @Query("SELECT * FROM chat_list_users")
    fun getChatUsers(): List<ChatUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatUsers(chatUsers: List<ChatUser>)

    @Update
    suspend fun update(chatUsers: ChatUser)

    @Query("DELETE FROM chat_list_users")
    suspend fun deleteChatUser()
}