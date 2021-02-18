package com.example.events.data.API


import java.sql.Date
import java.text.DecimalFormat
import java.text.SimpleDateFormat

object FormatData {

     fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat(" dd/MM/yyyy  HH:mm")
            val netDate = Date(1000 * s)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun getPrice(p: Double) : String{
        val dec = DecimalFormat("#,###.00")
        var credits = "R$ " + dec.format(p)
        credits = credits.replace(".", ",")

        return credits
    }

}