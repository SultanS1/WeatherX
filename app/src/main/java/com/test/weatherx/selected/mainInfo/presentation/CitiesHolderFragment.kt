package com.test.weatherx.selected.mainInfo.presentation

import android.widget.Toast
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.weatherx.R
import com.test.weatherx.core.architecture.NetworkStatus
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.databinding.FragmentCitiesHolderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesHolderFragment :
    BaseFragment<FragmentCitiesHolderBinding>(R.layout.fragment_cities_holder) {

    override val binding: FragmentCitiesHolderBinding by viewBinding()

    override val viewModel: CurrentWeatherViewModel by viewModel()

    private val adapter: CitiesAdapter by lazy {
        CitiesAdapter(
            childFragmentManager,
            viewLifecycleOwner.lifecycle
        )
    }

    override fun setupViews() {
        super.setupViews()
        viewModel.getAllLocations()
        binding.viewPager.adapter = adapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        observeSavedLocations()
    }

    private fun observeSavedLocations(){
        viewModel.savedLocations.observe(viewLifecycleOwner, Observer{ status ->
            when (status) {
                is NetworkStatus.Success -> {
                    adapter.setData(status.data)
                }
                is NetworkStatus.Error -> {
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkStatus.Loading -> {}
            }
        })
    }

}