package com.example.expense.network

import android.content.Context
import com.example.expense.networkSharedPreference.AuthenticationSharedPreference
import com.example.expense.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val token = AuthenticationSharedPreference(context = context).getToken()
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        token?.let {
            if (token != Constants.NO_TOKEN_PRESENT) {
                requestBuilder
                    .addHeader("accept", "application/json")
                    .addHeader("token", token)
            }
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}