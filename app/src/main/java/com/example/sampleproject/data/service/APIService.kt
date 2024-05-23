package com.example.sampleproject.data.service

import com.example.sampleproject.domain.network.model.Country
import retrofit2.Response
import retrofit2.http.GET

/**
 *  We create one interface class which will be used for declaration of all API calling functions.
 */
interface APIService {
    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(
    ): Response<ArrayList<Country>>
}