package com.example.chatapp.auth.repository

import com.example.chatapp.auth.models.SignInResponse
import com.example.chatapp.auth.request.SignInRequest
import com.example.chatapp.retrofit.ApiService
import retrofit2.Call

class SignInRepository(
    private val apiService: ApiService
) {
    fun signIn(
        email: String,
        password: String
    ): Call<SignInResponse> {
        val data = SignInRequest(email, password)
        return apiService.login(data)
    }
}