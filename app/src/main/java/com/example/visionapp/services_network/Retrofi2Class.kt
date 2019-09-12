package com.example.visionapp.services_network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofi2Class {

    const val BASE_URL = "https://funnyfractios000.000webhostapp.com/"

    val retrofitBuilder: Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: Services by lazy{
        retrofitBuilder
            .build()
            .create(Services::class.java)
    }

}
