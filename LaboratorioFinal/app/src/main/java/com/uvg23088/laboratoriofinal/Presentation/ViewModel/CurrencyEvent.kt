package com.uvg23088.laboratoriofinal.Presentation.ViewModel

interface CurrencyEvent {
    data class onCurrencyClick(val id: String): CurrencyEvent
    data object onCurrencyListClick: CurrencyEvent
    data object onLoadClick: CurrencyEvent
    data object  onOfflineClick: CurrencyEvent
}