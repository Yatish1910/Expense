package com.example.expense

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.expense.model.authenticationApiService.dataModel.request.LoginRequestBody
import com.example.expense.model.authenticationApiService.dataModel.request.RegistrationRequestBody
import com.example.expense.network.NetworkState
import com.example.expense.ui.theme.AuthViewModel
import com.example.expense.ui.theme.ExpenseTheme
import com.example.expense.utils.CommonComposable.ApiProgressBar
import com.example.expense.utils.CommonComposable.EditTextField
import com.example.expense.utils.CommonComposable.SubmitButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTheme {
                UserRegistrationScreen()
            }
        }
    }

    @Composable
    private fun CollectLoginUiStates() {
        val userLoginState by authViewModel.userLoginState.collectAsStateWithLifecycle()
        val registrationLoadingState by authViewModel.loadingState.collectAsStateWithLifecycle()
        if (registrationLoadingState) {
            ApiProgressBar(isLoading = true)
        } else {
            ApiProgressBar(isLoading = false)
            when (val state = userLoginState) {
                is NetworkState.Error -> {
                    Toast.makeText(this, state.errorCode.toString(), Toast.LENGTH_SHORT).show()
                }

                is NetworkState.Success -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }

    }
    @Composable
    private fun CollectRegistrationUiStates() {
        val userRegistrationState by authViewModel.registrationState.collectAsStateWithLifecycle()
        val registrationLoadingState by authViewModel.loadingState.collectAsStateWithLifecycle()
        if (registrationLoadingState) {
            ApiProgressBar(isLoading = true)
        } else {
            ApiProgressBar(isLoading = false)
            when (val state = userRegistrationState) {
                is NetworkState.Error -> {
                    Toast.makeText(this, state.errorCode.toString(), Toast.LENGTH_SHORT).show()
                }

                is NetworkState.Success -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }

    }

    @Composable
    fun UserRegistrationScreen(modifier: Modifier = Modifier) {
        CollectRegistrationUiStates()
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.authentication_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Adjust the scaling to fit the screen
            )
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Registration",
                    modifier = Modifier.padding(top = 46.dp, start = 38.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
                RegistrationForm()
            }
        }

    }

    @Composable
    private fun UserLoginScreen(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.authentication_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Adjust the scaling to fit the screen
            )
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Login",
                    modifier = Modifier.padding(top = 46.dp, start = 38.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
                LoginForm()
                CollectLoginUiStates()
            }
        }
    }

    @Composable
    fun LoginForm(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomCenter)
                .background(
                    color = Color.White, shape = RoundedCornerShape(
                        topStart = 38.dp, topEnd = 38.dp, // No rounding at the top
                        bottomStart = 0.dp, bottomEnd = 0.dp
                    )
                )
                .padding(horizontal = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.login_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(40.dp))
            LoginFormColumn(
                modifier = Modifier.padding(10.dp),
                onSignIn = { email, password ->
                    authViewModel.userLogin(
                        LoginRequestBody(
                            userEmail = email,
                            password = password
                        )
                    )
                }
            )
        }
    }

    @Composable
    fun RegistrationForm(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomCenter)
                .background(
                    color = Color.White, shape = RoundedCornerShape(
                        topStart = 38.dp, topEnd = 38.dp, // No rounding at the top
                        bottomStart = 0.dp, bottomEnd = 0.dp
                    )
                )
                .padding(horizontal = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.login_icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(180.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(40.dp))
            RegistrationFormColumn(
                modifier = Modifier.padding(10.dp),
                onRegistration = { email, password, firstName ->
                    authViewModel.userRegistration(
                        RegistrationRequestBody(
                            userEmail = email,
                            password = password,
                            firstName = firstName
                        )
                    )
                }
            )
        }
    }

    @Composable
    fun LoginFormColumn(modifier: Modifier = Modifier, onSignIn: (String, String) -> Unit) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            EditTextField(label = "Email", text = email, onTextChange = { email = it })
            EditTextField(label = "Password", text = password, onTextChange = { password = it })
            SubmitButton(label = "Sign in", onClick = {
                onSignIn(
                    email,
                    password
                )
            })
            GoogleButton(onClick = { })
            GetLoginText()
        }
    }

    @Composable
    fun RegistrationFormColumn(
        modifier: Modifier = Modifier,
        onRegistration: (String, String, String) -> Unit
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var firstName by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            EditTextField(label = "Email", text = email, onTextChange = { email = it })
            EditTextField(label = "Password", text = password, onTextChange = { password = it })
            EditTextField(label = "First Name", text = firstName, onTextChange = { firstName = it })
            SubmitButton(label = "Sign Up", onClick = {
                onRegistration(
                    email,
                    password,
                    firstName
                )
            })
            GoogleButton(onClick = { })
            GetLoginText()
        }
    }

    @Composable
    fun GoogleButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .padding(top = 43.dp)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .heightIn(48.dp),
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = colorResource(id = R.color.purple_9F46FF)
            ),
            border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.grey_D1D1D1)),
            shape = RoundedCornerShape(11.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, // Align content vertically in the center
                horizontalArrangement = Arrangement.Start, // Align content to the start (left)
                modifier = Modifier.fillMaxWidth() // Make Row fill the width of the Button
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_button_icon),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(35.dp)
                )

                Spacer(modifier = Modifier.width(8.dp)) // Space between the image and the text

                Text(
                    text = "Sign in with Google",
                    color = colorResource(id = R.color.grey_969696),
                    fontSize = 15.sp,
                )
            }
        }
    }

    @Composable
    fun GetLoginText(modifier: Modifier = Modifier) {
        Text(
            modifier = modifier
                .padding(top = 15.dp, bottom = 60.dp)
                .padding(horizontal = 10.dp),
            text = "Get your self register to experience seamless expense and payment management!",
            fontSize = 14.sp,
            color = colorResource(id = R.color.grey_737373),
        )
    }
}
