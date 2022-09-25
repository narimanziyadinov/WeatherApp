package com.example.myweather.presentation.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myweather.R
import com.example.myweather.data.retrofit.response.DailyDto
import com.example.myweather.databinding.ViewDailyForecastBinding
import com.example.myweather.domain.model.WeekForecast
import com.example.myweather.presentation.adapter.Utils.IMAGE_URI
import java.util.*

class DailyForecastAdapter(
    model: WeekForecast,
    private val context: Context
) : RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {

    private val forecasts: List<DailyDto> = model.daily
    private val calendar = Calendar.getInstance()

    inner class DailyForecastViewHolder(
        private val binding: ViewDailyForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailyDto, position: Int) = with(binding) {
            val uri: Uri =
                Uri.parse("$IMAGE_URI${item.weather.first().icon}@2x.png")
            tvDayOfWeek.text = setDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK) + position)
            tvTemperature.text = String.format(
                context.getString(R.string.temp),
                item.temp.day.toInt().toString()
            )
            ivWeather.load(uri)
        }

        private fun setDayOfWeek(get: Int): String =
            when (get % 7) {
                1 -> context.getString(R.string.sunday)
                2 -> context.getString(R.string.monday)
                3 -> context.getString(R.string.tuesday)
                4 -> context.getString(R.string.wednesday)
                5 -> context.getString(R.string.thursday)
                6 -> context.getString(R.string.friday)
                else -> context.getString(R.string.saturday)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder =
        DailyForecastViewHolder(
            ViewDailyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(forecasts[position], position)
    }

    override fun getItemCount(): Int = forecasts.size
}