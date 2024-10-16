package com.example.expense.domain.usecase

import com.example.expense.domain.AuthRepository
import com.example.expense.model.authenticationApiService.dataModel.request.LoginRequestBody
import com.example.expense.model.authenticationApiService.dataModel.response.LoginResponse
import com.example.expense.network.NetworkState
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UserLoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        userLoginRequestBody: LoginRequestBody
    ): NetworkState<LoginResponse> {
        return repository.userLogin(userLoginRequestBody)
    }
}