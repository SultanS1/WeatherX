package com.test.weatherx.core.architecture

sealed interface NetworkStatus<T>{

    class Success<T>(val data: T) : NetworkStatus<T>

    class Error<T>(val message: String, val data: T? = null) : NetworkStatus<T>

    class Loading<T> : NetworkStatus<T>
}