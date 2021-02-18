package com.example.events.presentation.events

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.events.R
import com.example.events.presentation.details.EventDetailsActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import kotlinx.android.synthetic.main.activity_events.*

class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val viewModel: EventsViewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)

        val status : Int = GooglePlayServicesUtil.isGooglePlayServicesAvailable(applicationContext);
        if(status == ConnectionResult.SUCCESS) {

        }else if(status == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED){
            Toast.makeText(
                applicationContext,
                R.string.att_google_play,
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.eventsLiveData.observe(this, Observer{
            it?.let { events ->
                with(recyclerBooks){
                    layoutManager = LinearLayoutManager(this@EventsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = EventsAdapter(events) { event ->
                            val intent = EventDetailsActivity.getStartIntent(this@EventsActivity, event.date, event.description,
                                event.image, event.longitude, event.latitude, event.price, event.title, event.id)
                            this@EventsActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.getEvents()

    }
}