package com.example.sampleproject.data.networkmodule

import com.example.sampleproject.data.service.APIService
import com.example.sampleproject.data.utils.Const.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Function to create and return the Retrofit instance for API calling.
 * Pass the base URL of the API.
 */
fun provideRetrofitInstance(): APIService {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    return retrofit.create(APIService::class.java)
}
