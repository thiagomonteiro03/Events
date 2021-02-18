package com.example.events.presentation.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.events.data.API.GET.ApiService
import com.example.events.data.model.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsViewModel : ViewModel() {
    val eventsLiveData: MutableLiveData<List<Event>> = MutableLiveData()

    fun getEvents() {
        CoroutineScope(Dispatchers.Main).launch{
            val response = ApiService.getEvents()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    eventsLiveData.value = response.body()
                }
            }

        }
    }

}