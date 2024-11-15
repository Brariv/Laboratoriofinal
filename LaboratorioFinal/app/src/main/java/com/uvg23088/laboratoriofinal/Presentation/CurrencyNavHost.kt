package com.uvg23088.laboratoriofinal.Presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg23088.laboratoriofinal.Presentation.CurrencyList.CurrencyListDestination
import com.uvg23088.laboratoriofinal.Presentation.CurrencyList.currencyList
import com.uvg23088.laboratoriofinal.Presentation.CurrencyProfile.currencyProfile
import com.uvg23088.laboratoriofinal.Presentation.CurrencyProfile.navigateToCurrencyProfile

@Composable
fun CurrencyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CurrencyListDestination,
        modifier = modifier
    ) {
        currencyList(navigateToCurrencyProfile = navController::navigateToCurrencyProfile)
        currencyProfile(onNavigateBack = navController::navigateUp)
    }

}