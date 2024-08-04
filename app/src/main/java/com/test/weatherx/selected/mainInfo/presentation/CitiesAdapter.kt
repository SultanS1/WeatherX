package com.test.weatherx.selected.mainInfo.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.weatherx.core.constants.Constants

class CitiesAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){

    private var items: List<CurrentWeatherUI> = emptyList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putParcelable(Constants.LOCATION, items[position])
        val fragment = CurrentDayFragment()
        fragment.arguments = bundle
        return fragment
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(new: List<CurrentWeatherUI>){
        items = new
        notifyDataSetChanged()
    }

}