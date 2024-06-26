package com.example.chatapp.chat.db

import androidx.room.TypeConverter
import com.example.chatapp.chat.models.MessageConversation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MessageListConverter {
    @TypeConverter
    fun fromString(value: String): List<MessageConversation> {
        val listType = object : TypeToken<List<MessageConversation>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<MessageConversation>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

//class MediaListConverter {
//
//    @TypeConverter
//    fun fromString(value: String): List<MediaBrowser.MediaItem> {
//        val listType = object : TypeToken<List<MediaBrowser.MediaItem>>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromList(list: List<MediaBrowser.MediaItem>): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//}

class MediaListConverter {
    @TypeConverter
    fun fromString(value: String): List<Any> {
        val listType = object : TypeToken<List<Any>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Any>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}