package com.example.expense.model.authenticationApiService.dataModel.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("message")
    val message: String?,

    @SerializedName("token")
    val token: String?
)