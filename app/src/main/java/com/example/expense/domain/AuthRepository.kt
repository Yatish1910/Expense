package com.example.expense.domain

import com.example.expense.model.authenticationApiService.dataModel.request.LoginRequestBody
import com.example.expense.model.authenticationApiService.dataModel.request.RegistrationRequestBody
import com.example.expense.model.authenticationApiService.dataModel.response.LoginResponse
import com.example.expense.model.authenticationApiService.dataModel.response.RegistrationResponse
import com.example.expense.network.NetworkState

interface AuthRepository {
    suspend fun userRegistration(
        registrationRequestBody: RegistrationRequestBody
    ): NetworkState<RegistrationResponse>

    suspend fun userLogin(
        loginRequestBody: LoginRequestBody
    ): NetworkState<LoginResponse>
}