package com.example.expense.network

sealed class NetworkState<out T> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error<out T>(
        val errorMessage: String,
        val errorCode: Int? = null
    ) :NetworkState<T>()
    data object Loading: NetworkState<Nothing>()
}