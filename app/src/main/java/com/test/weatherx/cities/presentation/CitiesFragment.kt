package com.test.weatherx.cities.presentation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.weatherx.R
import com.test.weatherx.core.architecture.NetworkStatus
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.databinding.FragmentCitiesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesFragment :
    BaseFragment<FragmentCitiesBinding>(R.layout.fragment_cities) {

    override val binding: FragmentCitiesBinding by viewBinding()

    override val viewModel: CitiesViewModel by viewModel()

    private val adapter: WeatherInfoAdapter by lazy {
        WeatherInfoAdapter()
    }

    override fun setupViews() {
        super.setupViews()
        setupSearchView()
        viewModel.getAllLocations()
        binding.citiesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.citiesRv.adapter = adapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        observeSearchedLocation()
        observeSavedLocations()
    }

    private fun observeSavedLocations(){
        viewModel.savedLocations.observe(viewLifecycleOwner, Observer{ status ->
            when (status) {
                is NetworkStatus.Success -> {
                    adapter.setList(status.data)
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkStatus.Error -> {
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkStatus.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun observeSearchedLocation(){
        viewModel.searchResult.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is NetworkStatus.Success -> {
                    adapter.setList(listOf(status.data))
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkStatus.Error -> {
                    Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkStatus.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupSearchView() {
        binding.searchView.setAdapter(ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.city_names)))

        binding.searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchView.text.toString()
                adapter.setList(listOf())
                if (query.isNotEmpty()) {
                    viewModel.searchLocation(query)
                    binding.searchView.clearFocus()
                    hideKeyboard()
                }else{
                    observeSavedLocations()
                }
                true
            } else {
                false
            }
        }

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.searchView.showDropDown()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}