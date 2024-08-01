package com.test.weatherx.selected.mainInfo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.weatherx.core.constants.Constants

class CitiesAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){

    private var items: List<String> = emptyList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable(Constants.CITY_KEY, items[position])
        val fragment = CurrentDayFragment()
        fragment.arguments = bundle
        return fragment
    }

    fun setData(new: List<String>){
        items = new
    }

}