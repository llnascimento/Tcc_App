package com.example.sbrotina.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("senha")
    val senha: String,
    @SerializedName("sexoUsuario")
    val sexoUsuario: String
)
