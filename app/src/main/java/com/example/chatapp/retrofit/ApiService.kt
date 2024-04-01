package com.example.chatapp.retrofit

import com.example.chatapp.chat.models.ChatListUsersResponse
import com.example.chatapp.chat.models.DeleteMessageResponse
import com.example.chatapp.chat.models.EditMessageResponse
import com.example.chatapp.chat.models.GetConversationResponse
import com.example.chatapp.chat.models.SendMessageResponse
import com.example.chatapp.chat.request.EditMessageRequest
import com.example.chatapp.chat.request.SendMessageRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface ApiService {
    @GET("api/v1/chat/get-conversations")
    fun getChatUsers(
        @Header("Authorization") token: String,
        ): Call<ChatListUsersResponse>

    @POST("api/v1/chat/send/{receiverId}")
    fun sendMessage(
        @Header("Authorization") token: String,
        @Path("receiverId") receiverId: String,
        @Body req: SendMessageRequest
    ): Call<SendMessageResponse>

    @GET("api/v1/chat/get-conversation/{receiverId}")
    fun getConversation(
        @Header("Authorization") token: String,
        @Path("receiverId") receiverId: String,
    ): Call<GetConversationResponse>

    @DELETE("api/v1/chat/delete-message/{messageId}")
    fun deleteMessage(
        @Header("Authorization") token: String,
        @Path("messageId") messageId: String
    ): Call<DeleteMessageResponse>

    @PATCH("api/v1/chat/edit-message/{messageId}")
    fun editMessage(
        @Header("Authorization") token: String,
        @Path("messageId") messageId: String,
        @Body req: EditMessageRequest
    ): Call<EditMessageResponse>

    @Multipart
    @POST("api/v1/chat/send/{receiverId}")
    fun sendImage(
        @Header("Authorization") token: String,
        @Path("receiverId") receiverId: String,
        @Part images: List<MultipartBody.Part>
    ): Call<SendMessageResponse>
}