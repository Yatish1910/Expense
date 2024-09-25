package com.example.expense.model.authenticationApiService.dataModel.request

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("email")
    val userEmail: String?,

    @SerializedName("password")
    val password: String
)