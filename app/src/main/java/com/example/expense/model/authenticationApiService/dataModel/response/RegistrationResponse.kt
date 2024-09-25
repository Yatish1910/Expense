package com.example.expense.model.authenticationApiService.dataModel.response

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("message")
    val message: String?
)