package com.example.expense.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense.domain.usecase.UserLoginUseCase
import com.example.expense.domain.usecase.UserRegistrationUseCase
import com.example.expense.model.authenticationApiService.dataModel.request.LoginRequestBody
import com.example.expense.model.authenticationApiService.dataModel.request.RegistrationRequestBody
import com.example.expense.model.authenticationApiService.dataModel.response.LoginResponse
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
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val loginUseCase: UserLoginUseCase
): ViewModel() {
    private val _registrationState = MutableStateFlow<NetworkState<RegistrationResponse>?>(null)
    val registrationState = _registrationState

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState

    private val _userLoginState = MutableStateFlow<NetworkState<LoginResponse>?>(null)
    val userLoginState = _userLoginState
    fun userRegistration(registrationRequestBody: RegistrationRequestBody) {
        viewModelScope.launch {
            _loadingState.emit(true)
            when(val response = userRegistrationUseCase.invoke(registrationRequestBody)) {
                is NetworkState.Error -> {
                    _registrationState.emit(response)
                    _loadingState.emit(false)
                }
                is NetworkState.Success -> {
                    _registrationState.emit(response)
                    _loadingState.emit(false)
                }
            }
        }
    }

    fun userLogin(userLoginRequestBody: LoginRequestBody) {
        viewModelScope.launch {
            _loadingState.emit(true)
            when(val response = loginUseCase.invoke(userLoginRequestBody)) {
                is NetworkState.Error -> {
                    _userLoginState.emit(response)
                    _loadingState.emit(false)
                }
                is NetworkState.Success -> {
                    _userLoginState.emit(response)
                    _loadingState.emit(false)
                }
            }
        }
    }
}