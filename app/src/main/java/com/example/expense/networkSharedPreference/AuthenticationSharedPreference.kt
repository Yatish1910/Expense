package com.example.expense.networkSharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.example.expense.utils.Constants

class AuthenticationSharedPreference(context: Context) {
    private val tokenSharedPreference: SharedPreferences =
        context.getSharedPreferences(Constants.AUTHENTICATION_PREFERENCE, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        tokenSharedPreference.edit()
            .putString(Constants.USER_TOKEN,token)
            .commit()
    }

    fun getToken(): String? {
        return tokenSharedPreference.getString(Constants.USER_TOKEN,Constants.NO_TOKEN_PRESENT)
    }

    fun clearToken() {
        tokenSharedPreference.edit()
            .remove(Constants.USER_TOKEN)
            .commit()
    }

}