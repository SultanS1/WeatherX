package com.test.weatherx.core.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * A base ViewModel class that provides a coroutine scope for launching coroutines
 * and handles coroutine exceptions.
 *
 * This ViewModel utilizes the Dispatchers.Main.immediate dispatcher for executing coroutines
 * on the main thread and a SupervisorJob to ensure that the failure of one child coroutine
 * does not cancel the scope.
 */
abstract class BaseViewModel: ViewModel() {

    /**
     * Coroutine scope for launching coroutines in the ViewModel.
     * The scope is tied to the ViewModel lifecycle and is canceled when the ViewModel is cleared.
     *
     * - Dispatchers.Main.immediate: Ensures that coroutines are executed immediately on the main thread.
     * - SupervisorJob: Allows child coroutines to fail independently without cancelling the entire scope.
     * - CoroutineExceptionHandler: Handles uncaught exceptions in the coroutine scope and logs the error message.
     */
    private val coroutineScope =
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob() + CoroutineExceptionHandler { coroutineContext, throwable ->
            println(throwable.message)
        })

    /**
     * Launches a coroutine in the ViewModel's coroutine scope.
     * The coroutine is executed on the main thread.
     *
     * @param operation The suspend function to be executed within the coroutine.
     */
    protected fun launch(operation: suspend () -> Unit) = coroutineScope.launch {
        operation()
    }

    /**
     * Called when the ViewModel is no longer used and will be destroyed.
     * This method cancels the coroutine scope to avoid memory leaks.
     */
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}