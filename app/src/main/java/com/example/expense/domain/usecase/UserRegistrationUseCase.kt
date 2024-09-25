package com.example.expense.domain.usecase

import com.example.expense.domain.AuthRepository
import com.example.expense.model.authenticationApiService.dataModel.request.RegistrationRequestBody
import com.example.expense.model.authenticationApiService.dataModel.response.RegistrationResponse
import com.example.expense.network.NetworkState
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UserRegistrationUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        registrationRequestBody: RegistrationRequestBody
    ) : NetworkState<RegistrationResponse> {
        return repository.userRegistration(registrationRequestBody)
    }
}