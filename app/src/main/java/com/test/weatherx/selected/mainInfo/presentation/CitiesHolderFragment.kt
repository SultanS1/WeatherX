package com.test.weatherx.selected.mainInfo.presentation

import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.weatherx.R
import com.test.weatherx.core.architecture.BaseViewModel
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.databinding.FragmentCitiesHolderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesHolderFragment :
    BaseFragment<FragmentCitiesHolderBinding>(R.layout.fragment_cities_holder) {

    override val binding: FragmentCitiesHolderBinding by viewBinding()

    override val viewModel: BaseViewModel by viewModel()

    private val adapter: CitiesAdapter by lazy {
        CitiesAdapter(
            childFragmentManager,
            viewLifecycleOwner.lifecycle
        )
    }

    override fun setupViews() {
        super.setupViews()
    }

}