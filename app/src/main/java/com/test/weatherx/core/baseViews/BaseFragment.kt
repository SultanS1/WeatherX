package com.test.weatherx.core.baseViews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.test.weatherx.core.architecture.BaseViewModel

/**
 * A base fragment class that sets up common functionality for fragments.
 *
 * @param layoutRes The layout resource ID for the fragment.
 */
abstract class BaseFragment<VB : ViewBinding>(layoutRes: Int) : Fragment(layoutRes) {

    /**
     * The view binding instance for the fragment.
     */
    abstract val binding: VB

    /**
     * The ViewModel associated with the fragment.
     */
    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    /**
     * Sets up the views in the fragment.
     * Override this method in child fragments to provide specific setup logic.
     */
    protected open fun setupViews() {
    }

    /**
     * Observes the ViewModel data and state changes.
     * Override this method in child fragments to provide specific observation logic.
     */
    protected open fun observeViewModel() {
    }
}

