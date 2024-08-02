package com.test.weatherx.cities.presentation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.weatherx.R
import com.test.weatherx.core.architecture.BaseViewModel
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.databinding.FragmentCitiesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesFragment :
    BaseFragment<FragmentCitiesBinding>(R.layout.fragment_cities) {

    override val binding: FragmentCitiesBinding by viewBinding()

    override val viewModel: BaseViewModel by viewModel()

    override fun setupViews() {
        super.setupViews()
        setupSearchView()
    }
    private fun setupSearchView() {
        binding.searchView.setAdapter(ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.city_names)))

        binding.searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchView.text.toString()
                if (query.isNotEmpty()) {
                    // Trigger the ViewModel's search method
                    // viewModel.searchCity(query)
                    // Clear focus and hide keyboard
                    binding.searchView.clearFocus()
                    hideKeyboard()
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