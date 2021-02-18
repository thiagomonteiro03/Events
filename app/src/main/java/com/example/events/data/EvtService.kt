package com.example.events.data

import com.example.events.data.model.Checkin
import com.example.events.data.model.Event
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface EvtService {

    @GET("events/")
    suspend fun getEventList() : Response<List<Event>>

    @Headers("Content-Type: application/json")
    @POST("checkin")
    fun addCheckin(@Body userSend : Checkin) : Call<Checkin>

}