package com.example.expense.domain

import com.example.expense.model.authenticationApiService.AuthenticationAPIService
import com.example.expense.model.authenticationApiService.dataModel.request.LoginRequestBody
import com.example.expense.model.authenticationApiService.dataModel.request.RegistrationRequestBody
import com.example.expense.model.authenticationApiService.dataModel.response.LoginResponse
import com.example.expense.model.authenticationApiService.dataModel.response.RegistrationResponse
import com.example.expense.network.NetworkState
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authenticationAPIService: AuthenticationAPIService
) : AuthRepository {
    override suspend fun userRegistration(
        registrationRequestBody: RegistrationRequestBody
    ): NetworkState<RegistrationResponse> {
        return try {
            val response = authenticationAPIService.userRegistration(registrationRequestBody)
            if (response.isSuccessful && response.body() != null) {
                NetworkState.Success(response.body()!!)
            } else {
                NetworkState.Error(response.message(), response.code())
            }
        } catch (e: Exception) {
            NetworkState.Error(e.message.toString())
        }
    }

    override suspend fun userLogin(
        loginRequestBody: LoginRequestBody
    ): NetworkState<LoginResponse> {
        return try {
            val response = authenticationAPIService.userLogin(loginRequestBody)
            if (response.isSuccessful && response.body() != null) {
                NetworkState.Success(response.body()!!)
            } else {
                NetworkState.Error(response.message(), response.code())
            }
        } catch (e: Exception) {
            NetworkState.Error(e.message.toString())
        }
    }
}