package com.example.events.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.events.data.API.GET.FormatData
import com.example.events.R
import com.example.events.data.API.POST.CheckinApiService
import com.example.events.data.model.Checkin
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.bottom_sheet_checkin.*

class EventDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setSupportActionBar(toolbar_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.teal_700)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.isHideable = false

        send_button.setOnClickListener {
            val sendService = CheckinApiService()
            val userInfo = Checkin(  eventId = intent.getIntExtra(EXTRA_ID,1),
                name = name_send.text.toString(),
                email = email_send.text.toString())

            sendService.addUser(userInfo) {
                if (it?.eventId != null&&FormatData.validateEmailFormat(email_send.text.toString())) {
                    Toast.makeText(this, R.string.check_in_sent_message,Toast.LENGTH_SHORT).show()
                    bottomSheetBehavior.isHideable = true
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                } else {
                    if (FormatData.validateEmailFormat(email_send.text.toString())){
                    Toast.makeText(this, R.string.check_in_error_message,Toast.LENGTH_SHORT).show()}
                    else Toast.makeText(this, R.string.check_email_error_message,Toast.LENGTH_SHORT).show()
                }
            }
        }

        shareButton.setOnClickListener{
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT,intent.getStringExtra(EXTRA_DESCRIPTION).toString() )
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }

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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val eventLocalAPI = LatLng(intent.getDoubleExtra(EXTRA_LATITUDE, 2.0), intent.getDoubleExtra(EXTRA_LONGITUDE, 2.0))
        mMap.addMarker(MarkerOptions().position(eventLocalAPI).title("Marker"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(eventLocalAPI))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocalAPI, 18F))

    }
}