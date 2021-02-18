package com.example.events.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.events.data.API.FormatData
import com.example.events.R
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setSupportActionBar(toolbar_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dateEvent.text = FormatData.getDateTime(intent.getLongExtra(EXTRA_DATE, 2))
        descEvent.text = intent.getStringExtra(EXTRA_DESCRIPTION)
        priceEvent.text = FormatData.getPrice(intent.getDoubleExtra(EXTRA_PRICE, 2.0))
        titleEvent.text = intent.getStringExtra(EXTRA_TITLE)
        Glide.with(this).load(intent.getStringExtra(EXTRA_IMAGE)).transition(
            DrawableTransitionOptions.withCrossFade()
        )
            .placeholder(R.drawable.ic_launcher_foreground).apply(RequestOptions.noAnimation())
            .into(imageEvent)
    }

    companion object {
        private const val EXTRA_DATE = "EXTRA_DATE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        private const val EXTRA_IMAGE = "EXTRA_IMAGE"
        private const val EXTRA_LONGITUDE = "EXTRA_LONGITUDE"
        private const val EXTRA_LATITUDE = "EXTRA_LATITUDE"
        private const val EXTRA_PRICE = "EXTRA_PRICE"
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_ID = "EXTRA_ID"

        fun getStartIntent(context: Context,date: Long, description: String, image: String, longitude: Double, latitude: Double, price:Double, title:String, id:String): Intent {
            return Intent(context, EventDetailsActivity::class.java).apply {
                putExtra(EXTRA_DATE, date)
                putExtra(EXTRA_DESCRIPTION, description)
                putExtra(EXTRA_IMAGE, image)
                putExtra(EXTRA_LONGITUDE, longitude)
                putExtra(EXTRA_LATITUDE, latitude)
                putExtra(EXTRA_PRICE, price)
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_ID, id)
            }
        }
    }
}