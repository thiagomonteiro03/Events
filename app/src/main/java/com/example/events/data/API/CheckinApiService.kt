package com.example.events.data.API

import com.example.events.data.model.Checkin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckinApiService {

    fun addUser(userData: Checkin, onResult: (Checkin?) -> Unit){
        val retrofit = RetrofitInitializer().buildService(EvtService::class.java)
        retrofit.addCheckin(userData).enqueue(
            object : Callback<Checkin> {
                override fun onFailure(call: Call<Checkin>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<Checkin>, response: Response<Checkin>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

}