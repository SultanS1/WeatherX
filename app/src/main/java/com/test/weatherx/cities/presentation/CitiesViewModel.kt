package com.test.weatherx.cities.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.weatherx.cities.domain.usecase.GetSavedLocationsUseCase
import com.test.weatherx.cities.domain.usecase.GetSearchResultUseCase
import com.test.weatherx.core.architecture.BaseViewModel
import com.test.weatherx.core.architecture.NetworkStatus

class CitiesViewModel(
    private val searchResultUseCase: GetSearchResultUseCase,
    private val savedLocationsUseCase: GetSavedLocationsUseCase
): BaseViewModel() {

    val searchResult: LiveData<NetworkStatus<WeatherUI>>
        get() = _searchResult
    private val _searchResult: MutableLiveData<NetworkStatus<WeatherUI>> = MutableLiveData()

    val savedLocations: LiveData<NetworkStatus<List<WeatherUI>>>
        get() = _savedLocations
    private val _savedLocations: MutableLiveData<NetworkStatus<List<WeatherUI>>> = MutableLiveData()

    fun searchLocation(cityName: String){
        _searchResult.postValue(NetworkStatus.Loading())
        launch {
            try {
                val weather = searchResultUseCase(cityName)
                if (weather != null) {
                    _searchResult.postValue(NetworkStatus.Success(weather.toUI()))
                } else {
                    _searchResult.postValue(NetworkStatus.Error("Failed to fetch weather data for $cityName"))
                }
            } catch (e: Exception) {
                _searchResult.postValue(NetworkStatus.Error("Failed to fetch data ${e.message}"))
            }
        }
    }

    fun getAllLocations() {
        _savedLocations.postValue(NetworkStatus.Loading())
        launch {
            try {
                val locations = savedLocationsUseCase()
                _savedLocations.postValue(NetworkStatus.Success(locations.map { it.toUI() }))
            } catch (e: Exception) {
                _savedLocations.postValue(NetworkStatus.Error("Failed to fetch locations: ${e.message}"))
            }
        }
    }

}