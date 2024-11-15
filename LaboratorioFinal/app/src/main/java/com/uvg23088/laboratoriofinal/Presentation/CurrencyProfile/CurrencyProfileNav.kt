package com.uvg23088.laboratoriofinal.Presentation.CurrencyProfile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyProfileDestination(val id: String)

fun NavController.navigateToCurrencyProfile(
    currencyId: String
) {
    this.navigate(CurrencyProfileDestination(
        id = currencyId
    ))
}

fun NavGraphBuilder.currencyProfile(
    onNavigateBack: () -> Unit
) {
    composable<CurrencyProfileDestination> {
        CurrencyProfileRoute(onNavigateBack = onNavigateBack)
    }
}