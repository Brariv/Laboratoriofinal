package com.uvg23088.laboratoriofinal.Presentation.ViewModel

import com.uvg23088.laboratoriofinal.domain.model.Currency

data class CurrencyState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isOffline: Boolean = false,
    val currencyList: List<Currency> = emptyList(),
    val currency: Currency? = null

)