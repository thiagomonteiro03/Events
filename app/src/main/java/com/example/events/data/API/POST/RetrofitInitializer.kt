package com.example.events.data.API.POST

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<Checkin> buildService(service: Class<Checkin>): Checkin{
        return retrofit.create(service)
    }
}