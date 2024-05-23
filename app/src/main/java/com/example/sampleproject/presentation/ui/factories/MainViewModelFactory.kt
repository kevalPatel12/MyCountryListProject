package com.example.sampleproject.presentation.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject.domain.network.repository.CountryRepository
import com.example.sampleproject.presentation.ui.viewmodels.MainViewModel

/**
 * Factory class responsible for creating instances of MainViewModel.
 * @param countryRepository The repository for fetching country data.
 */
class MainViewModelFactory(private val countryRepository: CountryRepository) : ViewModelProvider.Factory {

    /**
     * Creates an instance of the specified ViewModel class.
     * @param modelClass The class of the ViewModel to create.
     * @return An instance of the ViewModel.
     * @throws IllegalArgumentException if the ViewModel class is unknown.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(countryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
