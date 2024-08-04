package com.test.weatherx.selected.mainInfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.weatherx.core.architecture.BaseViewModel
import com.test.weatherx.core.architecture.NetworkStatus
import com.test.weatherx.selected.mainInfo.domain.usecase.GetAllLocationsUseCase
import com.test.weatherx.selected.mainInfo.domain.usecase.SaveLocationUseCase

class CurrentWeatherViewModel(
    private val saveLocationUseCase: SaveLocationUseCase,
    private val getAllLocationsUseCase: GetAllLocationsUseCase
): BaseViewModel() {

    val savedLocations: LiveData<NetworkStatus<List<CurrentWeatherUI>>>
        get() = _savedLocations
    private val _savedLocations: MutableLiveData<NetworkStatus<List<CurrentWeatherUI>>> = MutableLiveData()

    fun getAllLocations() {
        _savedLocations.postValue(NetworkStatus.Loading())
        launch {
            try {
                val locations = getAllLocationsUseCase()
                _savedLocations.postValue(NetworkStatus.Success(locations.map { it.toUI() }))
            } catch (e: Exception) {
                _savedLocations.postValue(NetworkStatus.Error("Failed to fetch locations: ${e.message}"))
            }
        }
    }

    fun saveLocation(location: CurrentWeatherUI){
        launch {
            saveLocationUseCase(location.toModel())
        }
    }

}