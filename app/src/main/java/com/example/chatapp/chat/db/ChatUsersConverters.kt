package com.example.chatapp.chat.db

import androidx.room.TypeConverter
import com.example.chatapp.chat.models.Image
import com.example.chatapp.chat.models.LastMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChatUsersConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromImage(image: Image): String {
        return gson.toJson(image)
    }

    @TypeConverter
    fun toImage(json: String): Image {
        val type = object : TypeToken<Image>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromLastMessage(lastMessage: LastMessage): String {
        return gson.toJson(lastMessage)
    }

    @TypeConverter
    fun toLastMessage(json: String): LastMessage {
        val type = object : TypeToken<LastMessage>() {}.type
        return gson.fromJson(json, type)
    }
}