package com.example.sbrotina.api

import com.example.sbrotina.model.LoginModel
import com.example.sbrotina.model.RegisterModel
import com.example.sbrotina.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface Endpoint {

    @POST("/sbrotina/api/usuario/Login")
    @Headers("Content-Type: application/json")
    fun authenticate(@Body userData: LoginModel) : Call<Usuario>


    @POST("/sbrotina/api/usuario/Adicionar")
    @Headers("Content-Type: application/json")
    fun create(@Body userData: RegisterModel) : Call<Usuario>
}