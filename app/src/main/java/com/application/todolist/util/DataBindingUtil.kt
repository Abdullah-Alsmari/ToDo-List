package com.application.todolist.util

import android.graphics.Color
import android.text.format.DateUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("showDate")
fun setDate(textView: TextView, date: Long) {
    if (date > 0) {
        val currentTime = Calendar.getInstance().timeInMillis
        val formatter = SimpleDateFormat("dd/MM");
        val dateString = formatter.format(Date(date))
        val text = if (DateUtils.isToday(date) || currentTime < date) {
            "Coming"
        } else {
            "Past"
        }
        textView.text = dateString + "    " + text
    }

}


@BindingAdapter("cardColor")
fun setCardBackgroundColor(cardView: CardView, isChecked: Boolean) {
    if (isChecked)
        cardView.setCardBackgroundColor(Color.parseColor("#7CFC00"))
    else
        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

}
