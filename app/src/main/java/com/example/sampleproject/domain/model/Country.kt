package com.example.sampleproject.domain.network.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for holding API response data schema
 */
data class Country(
    @SerializedName("capital") var capital: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("region") var region: String? = null,
)