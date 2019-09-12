package com.example.visionapp.services_network

import com.example.visionapp.baseResponseClasses.BaseResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface  Services{

    @FormUrlEncoded
    @POST("registrarVision.php/")
    fun registrationProcess(@Field("identificador") id_cliente: String,
                            @Field("nombre") nombre: String,
                            @Field("apellidos") apellidos: String,
                            @Field("celular") celular: String,
                            @Field("correo") correo: String): Call<BaseResponse>


}