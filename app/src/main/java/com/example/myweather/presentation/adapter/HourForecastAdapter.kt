package com.example.myweather.presentation.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myweather.R
import com.example.myweather.data.retrofit.response.HourWeatherDto
import com.example.myweather.databinding.ViewHourForecastBinding
import com.example.myweather.domain.model.Forecast
import com.example.myweather.presentation.adapter.Utils.IMAGE_URI

class HourForecastAdapter(
    model: Forecast,
    private val context: Context
) : RecyclerView.Adapter<HourForecastAdapter.HourForecastViewHolder>() {

    private val forecasts: List<HourWeatherDto> = model.list

    inner class HourForecastViewHolder(
        private val binding: ViewHourForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HourWeatherDto) = with(binding) {
            val uri: Uri =
                Uri.parse("$IMAGE_URI${item.weather[0].icon}@2x.png")
            ivHourForecast.load(uri)
            tvHour.text = item.dtTxt.substring(11, 16)
            tvTemperature.text = String.format(context.getString(R.string.temp), item.main.temp.toInt())
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourForecastViewHolder =
            HourForecastViewHolder(
                ViewHourForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

        override fun onBindViewHolder(holder: HourForecastViewHolder, position: Int) {
            holder.bind(forecasts[position])
        }

        override fun getItemCount(): Int = forecasts.size
}