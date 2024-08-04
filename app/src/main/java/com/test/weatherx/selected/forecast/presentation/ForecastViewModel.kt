package com.test.weatherx.selected.forecast.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.weatherx.core.architecture.BaseViewModel
import com.test.weatherx.core.architecture.NetworkStatus
import com.test.weatherx.selected.forecast.domain.usecase.GetForeCastUseCase

class ForecastViewModel(
    private val getForeCastUseCase: GetForeCastUseCase
): BaseViewModel() {

    val forecastResult: LiveData<NetworkStatus<List<ForecastUI>>>
        get() = _forecastResult
    private val _forecastResult: MutableLiveData<NetworkStatus<List<ForecastUI>>> = MutableLiveData()

    fun getForecast(cityName: String){
        launch {
            try {
                val forecast = getForeCastUseCase(cityName)
                _forecastResult.postValue(forecast?.let { NetworkStatus.Success(it.map { it.toUI() }) })
            } catch (e: Exception) {
                _forecastResult.postValue(NetworkStatus.Error("Failed to fetch locations: ${e.message}"))
            }
        }
    }

}