package com.example.sbrotina.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("senha")
    val senha: String
)
