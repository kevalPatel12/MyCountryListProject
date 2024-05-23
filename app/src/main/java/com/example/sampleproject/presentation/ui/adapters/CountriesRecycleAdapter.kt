// Presentation layer - CountriesRecyclerAdapter.kt
package com.example.sampleproject.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.databinding.RawCountriesItemLayoutBinding
import com.example.sampleproject.domain.network.model.Country
import com.example.sampleproject.data.utils.Const.Companion.COMMA_SEPARATOR

/**
 * RecyclerView adapter responsible for displaying a list of countries.
 */
class CountriesRecyclerAdapter :
    RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder>() {

    /**
     * ViewHolder class to hold the views for each item in the RecyclerView.
     */
    inner class CountriesViewHolder(private val itemBinding: RawCountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        /**
         * Binds the country data to the views in the ViewHolder.
         */
        fun bind(country: Country) {
            itemBinding.apply {
                nameOfCountry.text = country.name + COMMA_SEPARATOR
                regionOfCountry.text = country.region
                codeOfCountry.text = country.code
                capitalOfCountry.text = country.capital
            }
        }
    }

    // List of countries to be displayed
    private var countriesList: List<Country> = emptyList()

    /**
     * Sets the data for the adapter and notifies any observers of the data set change.
     */
    fun setData(countries: List<Country>) {
        this.countriesList = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        // Inflate the layout for each item in the RecyclerView
        val itemBinding = RawCountriesItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountriesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    override fun getItemCount() = countriesList.size
}