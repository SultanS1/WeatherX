package com.test.weatherx.selected.mainInfo.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.test.weatherx.R
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.core.constants.Constants
import com.test.weatherx.databinding.FragmentCurrentDayBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class CurrentDayFragment :
    BaseFragment<FragmentCurrentDayBinding>(R.layout.fragment_current_day) {

    override val binding: FragmentCurrentDayBinding by viewBinding()

    override val viewModel: CurrentWeatherViewModel by viewModel()

    override fun setupViews() {
        super.setupViews()
        getParameters()
    }

    private fun getParameters(){
        val argument = arguments?.getParcelable<CurrentWeatherUI>(Constants.LOCATION)
        if (argument != null) {
            setupArgument(argument)
            if (!argument.saved) {
                binding.addBtn.visibility = View.VISIBLE
            } else {
                binding.addBtn.visibility = View.GONE
            }
        } else {
            println(getString(R.string.no_argument_passed_for_location))
        }
    }

    private fun setupArgument(location: CurrentWeatherUI) = with(binding) {
        locationTxt.text = location.cityName
        dateTxt.text = location.dateTime
        humidityTxt.text = location.humidity
        typeTxt.text = location.typeDescription
        windSpeedTxt.text = location.windSpeed
        Glide.with(imageView).load("http:${location.typeIcon}").into(imageView)
        addBtn.setOnClickListener {
            viewModel.saveLocation(location.copy(saved = true))
            Toast.makeText(requireContext(), getString(R.string.the_location_is_saved), Toast.LENGTH_SHORT).show()
        }
        moreBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.CITY_KEY, location.cityName)
            findNavController().navigate(R.id.action_citiesHolderFragment_to_comingDaysFragment, bundle)
        }
    }

}