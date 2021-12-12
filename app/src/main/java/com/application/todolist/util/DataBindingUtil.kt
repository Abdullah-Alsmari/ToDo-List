package com.application.todolist.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.format.DateUtils
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.application.todolist.R
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*

@SuppressLint("ResourceAsColor")
@BindingAdapter("showDate")
fun setDate(textView: TextView, date: Long) {
    if (date > 0) {
        val currentTime = Calendar.getInstance().timeInMillis
        val formatter = SimpleDateFormat("dd/MM");
        val dateString = formatter.format(Date(date))
        var text = ""
        if (DateUtils.isToday(date) || currentTime < date) {
            text = "Coming"
            textView.setTextColor(Color.parseColor("#000000"))

        } else {
            text = "Past"
            textView.setTextColor(Color.parseColor("#000000"))
        }
        textView.text = text
    }

}

@BindingAdapter("showDay")
fun setDay(textView: TextView, date: Long) {

    val c = Calendar.getInstance();
    c.setTimeInMillis(date);

    val month_date = SimpleDateFormat("EE")
    val month_name = month_date.format(c.getTime())
    textView.text = month_name


}

@BindingAdapter("showOnyDate")
fun setDateOnly(textView: TextView, date: Long) {
    val c = Calendar.getInstance();
    c.setTimeInMillis(date);
    val dayNum = c.get(Calendar.DAY_OF_MONTH)

    textView.text = dayNum.toString()
}


@BindingAdapter("showMonth")
fun setMonth(textView: TextView, date: Long) {
    val c = Calendar.getInstance();
    c.setTimeInMillis(date);

    val month_date = SimpleDateFormat("MMM")
    val month_name = month_date.format(c.getTime())
    textView.text = month_name
}


@BindingAdapter("cardColor", "error")
fun setCardBackgroundColor(cardView: CardView, date: Long, isChecked: Boolean) {
    if (isChecked) {
        cardView.setCardBackgroundColor(Color.parseColor("#6AD5A6"))
    } else {
        if (date > 0) {
            val currentTime = Calendar.getInstance().timeInMillis
            if (DateUtils.isToday(date) || currentTime < date) {
                cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

            } else {
                cardView.setCardBackgroundColor(Color.parseColor("#E9735E"))
            }
        }


    }

}






