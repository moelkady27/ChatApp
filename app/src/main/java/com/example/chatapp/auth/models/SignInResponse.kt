package com.example.chatapp.auth.models

data class SignInResponse(
    val status: String,
    val token: String,
    val message: String,
    val isVerified: Boolean,
    val userId: String
)