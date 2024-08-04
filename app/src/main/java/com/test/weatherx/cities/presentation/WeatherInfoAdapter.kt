package com.test.weatherx.cities.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.weatherx.R
import com.test.weatherx.databinding.ItemCityBinding

class WeatherInfoAdapter: RecyclerView.Adapter<WeatherInfoAdapter.ViewHolder>() {

    private var items: List<WeatherUI> = emptyList();

    var clickToDetails: ((WeatherUI)->Unit)? = null


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var binding = ItemCityBinding.bind(itemView)
        fun bind(info: WeatherUI){
            binding.tempValueTxt.text = info.temp
            binding.locationTxt.text = info.cityName
            binding.highestValueTxt.text = info.tempH
            binding.lowestValueTxt.text = info.tempM
            binding.weatherTypeTxt.text = info.typeDescription
            Glide.with(binding.typeIc).load("http:${info.typeIcon}").into(binding.typeIc)
            binding.root.setOnClickListener {
                clickToDetails?.invoke(info)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<WeatherUI>){
        items = newList
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

}