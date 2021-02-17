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
import com.example.events.data.model.Event
import kotlinx.android.synthetic.main.fragment_event.view.*
import java.sql.Date
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class EventsAdapter (private val eventList: List<Event>,
                     private val listener: OnItemClickListener
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_event,
                parent,
                false
        )
        return EventViewHolder(itemView)
    }

    override fun getItemCount() = eventList.count()

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = eventList[position]

        Glide.with(holder.imageView.context).load(currentItem.image).transition(withCrossFade())
                .placeholder(R.drawable.ic_launcher_foreground).apply(RequestOptions.noAnimation())
                .into(holder.imageView)
        holder.textView1.text = currentItem.title
        holder.textView2.text = getPrice(currentItem.price)
        holder.textView3.text = getDateTime(currentItem.date)
    }

    private fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(1000 * s)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    private fun getPrice(p: Double): String {
        val dec = DecimalFormat("#,###.00")
        var credits = "R$ " + dec.format(p)
        credits = credits.replace(".", ",")

        return credits
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
        val imageView : ImageView = itemView.event_image
        val textView1: TextView = itemView.event_name
        val textView2: TextView = itemView.event_price
        val textView3: TextView = itemView.event_date


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }
}