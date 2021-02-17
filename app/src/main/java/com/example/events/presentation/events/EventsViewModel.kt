package com.example.events.presentation.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.events.data.model.Event

class EventsViewModel : ViewModel() {

    val eventsLiveData: MutableLiveData<List<Event>> = MutableLiveData()

    fun getEvents() {
        eventsLiveData.value = createFakeEvents()
    }

    fun createFakeEvents(): List<Event>{
        return listOf(
            Event(2, "sei la 1", "olhai"),
            Event(2, "sei la 2", "olhai2"),
            Event(2, "sei la 3", "olhai3"),
            Event(2, "sei la 4", "olhai4")
        )
    }

}