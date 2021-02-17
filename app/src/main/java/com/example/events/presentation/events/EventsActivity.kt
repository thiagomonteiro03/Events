package com.example.events.presentation.events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.events.R
import com.example.events.data.model.Event
import kotlinx.android.synthetic.main.activity_events.*

class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val viewModel: EventsViewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)

        viewModel.eventsLiveData.observe(this, Observer{
            it?.let { events ->
                with(recyclerBooks){
                    layoutManager = LinearLayoutManager(this@EventsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = EventsAdapter(getEvents())
                }
            }
        })

        viewModel.getEvents()

    }

    fun getEvents(): List<Event>{
        return listOf(
            Event(2, "sei la 1", "olhai"),
            Event(2, "sei la 2", "olhai2"),
            Event(2, "sei la 3", "olhai3"),
            Event(2, "sei la 4", "olhai4")
        )
    }
}