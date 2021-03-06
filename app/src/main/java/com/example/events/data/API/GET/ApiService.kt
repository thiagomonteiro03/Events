package com.example.events.data.API.GET

import com.example.events.data.API.EvtService
import com.example.events.data.model.Checkin
import com.example.events.data.model.Event
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    private val api : EvtService = Retrofit.Builder()
        .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EvtService::class.java)

    suspend fun getEvents() : Response<List<Event>> = api.getEventList()


}