// Presentation layer - MainActivity.kt
package com.example.sampleproject.presentation.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleproject.data.repository.CountryRepositoryImpl
import com.example.sampleproject.databinding.ActivityMainBinding
import com.example.sampleproject.data.networkmodule.provideRetrofitInstance
import com.example.sampleproject.presentation.ui.adapters.CountriesRecyclerAdapter
import com.example.sampleproject.presentation.ui.viewmodels.MainViewModel
import com.example.sampleproject.presentation.ui.factories.MainViewModelFactory

/**
 * The primary activity responsible for presenting a list of countries.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var adapterCountries: CountriesRecyclerAdapter
    private lateinit var viewModelMain: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initializeRecyclerView()
        setupViewModel()
        observeViewModelChanges()
    }

    /**
     * Configures the RecyclerView to display the list of countries.
     */
    private fun initializeRecyclerView() {
        adapterCountries = CountriesRecyclerAdapter()
        activityMainBinding.countryRecyclerView.apply {
            adapter = adapterCountries
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    /**
     * Sets up the ViewModel using ViewModelFactory and initializes the MainViewModel.
     */
    private fun setupViewModel() {
        val serviceApi = provideRetrofitInstance()
        val repository = CountryRepositoryImpl(serviceApi)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModelMain = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModelMain.fetchCountriesFromRepository()
    }

    /**
     * Observes changes in the ViewModel and updates the UI accordingly.
     */
    private fun observeViewModelChanges() {
        viewModelMain.countryLiveData.observe(this, Observer { countries ->
            countries?.let {
                adapterCountries.setData(it)
            } ?: run {
                Toast.makeText(this, "An unexpected error occurred!", Toast.LENGTH_SHORT).show()
            }
        })

        viewModelMain.isShowProgressLiveData.observe(this, Observer { showProgress ->
            activityMainBinding.mainProgressBar.visibility = if (showProgress) View.VISIBLE else View.GONE
        })

        viewModelMain.errorMessageLiveData.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }
}
