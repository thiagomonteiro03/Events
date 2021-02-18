package com.example.events.presentation.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.example.events.R
import com.example.events.data.API.FormatData
import com.example.events.data.model.Event
import kotlinx.android.synthetic.main.fragment_event.view.*
import java.sql.Date
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class EventsAdapter (private val eventList: List<Event>,
                     val onItemClickListener: ((event: Event) -> Unit)
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_event,
                parent,
                false
        )
        return EventViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = eventList.count()

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = eventList[position]
        holder.bindView(eventList[position])

        Glide.with(holder.image.context).load(currentItem.image).transition(withCrossFade())
                .placeholder(R.drawable.ic_launcher_foreground).apply(RequestOptions.noAnimation())
                .into(holder.image)
        holder.price.text = FormatData.getPrice(currentItem.price)
        holder.date.text = FormatData.getDateTime(currentItem.date)
    }

    class EventViewHolder(itemView: View, val onItemClickListener: ((event: Event) -> Unit)) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.event_image
        val title: TextView = itemView.event_name
        val price: TextView = itemView.event_price
        val date: TextView = itemView.event_date
        val description: TextView = itemView.event_price

        fun bindView(event: Event){
            title.text = event.title
            description.text = event.description

            itemView.setOnClickListener {
                onItemClickListener.invoke(event)
            }

        }

    }
}