package com.example.expense.model.authenticationApiService

import com.example.expense.model.authenticationApiService.dataModel.request.LoginRequestBody
import com.example.expense.model.authenticationApiService.dataModel.request.RegistrationRequestBody
import com.example.expense.model.authenticationApiService.dataModel.response.LoginResponse
import com.example.expense.model.authenticationApiService.dataModel.response.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPIService {
    @POST("auth/register")
    suspend fun userRegistration(
        @Body registrationRequestBody: RegistrationRequestBody
    ) : Response<RegistrationResponse>

    @POST("auth/login")
    suspend fun userLogin(
        @Body loginRequestBody: LoginRequestBody
    ) : Response<LoginResponse>
}