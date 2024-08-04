package com.test.weatherx.selected.forecast.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.weatherx.R
import com.test.weatherx.databinding.ItemForecastBinding

class ForecastAdapter: RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private var items: List<ForecastUI> = emptyList();

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var binding = ItemForecastBinding.bind(itemView)
        fun bind(info: ForecastUI){
            binding.highestValueTxt.text = "${info.maxTemp}°"
            binding.lowestValueTxt.text = "${info.minTemp}°"
            Glide.with(binding.typeIc).load("http:${info.typeIcon}")
            binding.typeTxt.text = info.typeDescription
            binding.dateTxt.text = info.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<ForecastUI>){
        items = newList
        notifyDataSetChanged()
    }

}