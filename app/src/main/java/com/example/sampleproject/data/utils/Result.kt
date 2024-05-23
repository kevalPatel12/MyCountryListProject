package com.example.sampleproject.data.utils

/**
 * A sealed class representing the result of an operation.
 */
sealed class Result<out T> {
    /**
     * Represents a successful result with associated data.
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents an error result with an associated error message.
     */
    data class Error(val message: String) : Result<Nothing>()
}
