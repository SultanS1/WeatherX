package com.test.weatherx.selected.moreInfo.presentation

import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.weatherx.R
import com.test.weatherx.core.architecture.BaseViewModel
import com.test.weatherx.core.baseViews.BaseFragment
import com.test.weatherx.databinding.FragmentComingDaysBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ComingDaysFragment :
    BaseFragment<FragmentComingDaysBinding>(R.layout.fragment_coming_days) {

    override val binding: FragmentComingDaysBinding by viewBinding()

    override val viewModel: BaseViewModel by viewModel()

    override fun setupViews() {
        super.setupViews()
    }

}