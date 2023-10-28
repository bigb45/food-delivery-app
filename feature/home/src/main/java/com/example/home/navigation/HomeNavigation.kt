package com.example.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.home.welcome.Welcome
import java.net.URLEncoder

private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()
internal const val userIdArgument = "userId"

const val homeRoute = "home_route"

fun NavController.navigateToWelcome(navOptions: NavOptions? = null, userId: String){
    val encodedUserId = URLEncoder.encode(userId, URL_CHARACTER_ENCODING)

    this.navigate(route = "$homeRoute/$encodedUserId", navOptions = navOptions)
}

fun NavGraphBuilder.welcomeScreen(){
    composable(
        route = "$homeRoute/{$userIdArgument}",
        arguments = listOf(
            navArgument(userIdArgument) { type = NavType.StringType },
        ),
    ){
        Welcome(
            onContinueClick = {},
            onSignOut = {}
        )
    }
}