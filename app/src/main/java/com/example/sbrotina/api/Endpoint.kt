package com.example.sbrotina.api

import com.example.sbrotina.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Headers
import retrofit2.http.POST

interface Endpoint {

    @POST("/sbrotina/api/usuario/Login")
    @Headers("Content-Type: application/json")
    fun authenticate(@Body userData: LoginModel) : Call<Usuario>


    @POST("/sbrotina/api/usuario/Adicionar")
    @Headers("Content-Type: application/json")
    fun create(@Body userData: RegisterModel) : Call<Usuario>


    @POST("/sbrotina/api/Tarefa/Cadastrar")
    @Headers("Content-Type: application/json")
    fun createtask(@Body userData: RegisterTarefaModel) : Call<Tarefa>

    @DELETE("/sbrotina/api/Tarefa/Deletar")
    @Headers("Content-Type: application/json")
    fun deletetask() : Call<Void>

}