package com.uvg23088.laboratoriofinal.Presentation.CurrencyList

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CurrencyListDestination

fun NavGraphBuilder.currencyList(
    navigateToCurrencyProfile: (String) -> Unit
) {
    composable<CurrencyListDestination> {
        CurrencyListRoute(
            onCurrencyClick = navigateToCurrencyProfile
        )
    }
}