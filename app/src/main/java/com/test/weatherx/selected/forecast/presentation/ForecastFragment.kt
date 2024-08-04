package com.test.weatherx.selected.forecast.presentation

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.weatherx.R
import com.test.weatherx.core.architecture.NetworkStatus
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.core.constants.Constants
import com.test.weatherx.databinding.FragmentForecastBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ForecastFragment :
    BaseFragment<FragmentForecastBinding>(R.layout.fragment_forecast) {

    override val binding: FragmentForecastBinding by viewBinding()

    override val viewModel: ForecastViewModel by viewModel()

    private val adapter: ForecastAdapter by lazy {
        ForecastAdapter()
    }

    override fun setupViews() {
        super.setupViews()
        val argument = arguments?.getString(Constants.CITY_KEY)
        binding.locationTxt.text = argument
        argument?.let { viewModel.getForecast(it) }
        binding.comingDaysInfoRv.layoutManager = LinearLayoutManager(requireContext())
        binding.comingDaysInfoRv.adapter = adapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        observeForecast()
    }

    private fun observeForecast(){
        viewModel.forecastResult.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is NetworkStatus.Success -> {
                    adapter.setList(status.data)
//                    binding.progressBar.visibility = View.GONE
                }
                is NetworkStatus.Error -> {
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
//                    binding.progressBar.visibility = View.GONE
                }
                is NetworkStatus.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

}