package com.example.sbrotina.model

import com.google.gson.annotations.SerializedName

data class Tarefa(
    @SerializedName("id")
    val id: Int,

    @SerializedName("nome")
    val nome: String,

    @SerializedName("descricao")
    val descricao: String,

    @SerializedName("dataTermino")
    val dataTermino: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("usuarioId")
    val usuarioId: Int


)
