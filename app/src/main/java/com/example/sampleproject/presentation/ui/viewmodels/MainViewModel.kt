// Presentation layer - MainViewModel.kt
package com.example.sampleproject.presentation.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.domain.network.repository.CountryRepository
import com.example.sampleproject.domain.network.model.Country
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.sampleproject.data.utils.Result

/**
 * ViewModel class responsible for handling business logic and communication between the UI and the data layer.
 */
class MainViewModel(private val countryRepository: CountryRepository) : ViewModel() {
    // LiveData to hold the list of countries retrieved from the repository
    val countryLiveData = MutableLiveData<List<Country>>()

    // LiveData to hold any error messages
    val errorMessageLiveData = MutableLiveData<String>()

    // LiveData to indicate whether progress is being shown
    val isShowProgressLiveData = MutableLiveData<Boolean>()

    // Job to manage coroutines
    private var jobInstance: Job? = null

    // Coroutine exception handler to handle exceptions
    private val exceptionHandlerInstance = CoroutineExceptionHandler { _, throwable ->
        handleError("Exception handled : ${throwable.localizedMessage}")
    }

    /**
     * Fetches the list of countries from the repository.
     */
    fun fetchCountriesFromRepository() {
        isShowProgressLiveData.value = true
        jobInstance = viewModelScope.launch(exceptionHandlerInstance) {
            val result = countryRepository.getCountries()
            if (result is Result.Success) {
                countryLiveData.postValue(result.data)
            } else if (result is Result.Error) {
                handleError("Error: ${result.message}")
            }
            isShowProgressLiveData.postValue(false)
        }
    }

    /**
     * Handles error messages.
     */
    private fun handleError(message: String) {
        errorMessageLiveData.value = message
        isShowProgressLiveData.value = false
    }

    override fun onCleared() {
        super.onCleared()
        jobInstance?.cancel()
    }
}
