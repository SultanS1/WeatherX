package com.test.weatherx.selected.mainInfo.presentation

import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.weatherx.R
import com.test.weatherx.core.architecture.BaseViewModel
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.databinding.FragmentCurrentDayBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentDayFragment :
    BaseFragment<FragmentCurrentDayBinding>(R.layout.fragment_current_day) {

    override val binding: FragmentCurrentDayBinding by viewBinding()
    override val viewModel: BaseViewModel by viewModel()

    override fun setupViews() {
        super.setupViews()
    }

}