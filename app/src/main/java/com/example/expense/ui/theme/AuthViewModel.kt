package com.example.expense.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense.domain.usecase.UserRegistrationUseCase
import com.example.expense.model.authenticationApiService.dataModel.request.RegistrationRequestBody
import com.example.expense.model.authenticationApiService.dataModel.response.RegistrationResponse
import com.example.expense.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRegistrationUseCase: UserRegistrationUseCase
): ViewModel() {
    private val _registrationState = MutableStateFlow<NetworkState<RegistrationResponse>>(NetworkState.Loading)
    val registrationState : StateFlow<NetworkState<RegistrationResponse>?> = _registrationState.asStateFlow()
    fun userRegistration(registrationRequestBody: RegistrationRequestBody) {
        viewModelScope.launch {
            when(val response = userRegistrationUseCase.invoke(registrationRequestBody)) {
                is NetworkState.Error -> {
                    _registrationState.emit(response)
                }
                NetworkState.Loading -> {
                    _registrationState.emit(NetworkState.Loading)
                }
                is NetworkState.Success -> {
                    _registrationState.emit(response)
                }
            }
        }
    }
}