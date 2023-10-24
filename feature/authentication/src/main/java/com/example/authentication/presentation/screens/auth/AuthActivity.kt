package com.example.authentication.presentation.screens.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authentication.presentation.screens.auth.google_login.GoogleSignInViewModel
import com.example.core.ui.theme.FoodDeliveryTheme
import com.example.create_account.navigation.createAccount
import com.example.data.models.AuthResult
import com.example.email.navigation.emailLogin
import com.example.facebook.navigation.facebookLogin
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            FoodDeliveryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val googleSignInViewModel = viewModel<GoogleSignInViewModel>()
                    val state by googleSignInViewModel.state.collectAsState()

                    LaunchedEffect(key1 = state) {
                        when (state) {
                            AuthResult.Cancelled -> {}
                            is AuthResult.Error -> {
                                navController.navigate("sign_in_result")
                            }

                            is AuthResult.Success -> {
                                navController.navigate("sign_in_result")
                            }

                            AuthResult.Loading -> {}

                            else -> {}
                        }
                    }
//                    TODO:  navigate to main app screen if user already signed in

                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(Screen.LoginScreen.route) {
                            LoginMethods(navController = navController)
                        }

                        createAccount()

                        emailLogin()

                        facebookLogin()


                        composable(Screen.SignInResultScreen.route) {
                            SignInResult(navController = navController)
                        }

                    }
                }
            }
        }
    }
}


