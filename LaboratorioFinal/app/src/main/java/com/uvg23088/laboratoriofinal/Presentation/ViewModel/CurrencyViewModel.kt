package com.uvg23088.laboratoriofinal.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.ejercicioslabs.ejercicios.ktor.di.KtorDependencies
import com.uvg23088.laboratoriofinal.data.network.KtorCoinCapApi
import com.uvg23088.laboratoriofinal.data.repo.LocalCurrencyRepository
import com.uvg23088.laboratoriofinal.di.RoomDependencies
import com.uvg23088.laboratoriofinal.domain.network.CoinCapApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class CurrencyViewModel (
    private val currencyRepository: LocalCurrencyRepository
): ViewModel() {
    private val _state = MutableStateFlow(CurrencyState())
    val state = _state.asStateFlow()


    init {
        getCurrencies()
    }


    fun onEvent(event: CurrencyEvent) {
        when (event) {
            CurrencyEvent.onCurrencyListClick -> getCurrencies()
            is CurrencyEvent.onCurrencyClick -> getCurrencyProfile(event.id)
            is CurrencyEvent.onLoadClick -> getCurrencies()
            is CurrencyEvent.onOfflineClick -> makeOffline()


        }
    }

    private fun getCurrencies() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isError = false
                )
            }

            if (!_state.value.isOffline) {
                try {
                    val Currencies = currencyRepository.getCurrenciesFromNetwork()
                    if (Currencies.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                currencyList = Currencies
                            )
                        }
                    } else {
                        println("Error from ktor")
                        _state.update {
                            it.copy(

                                isLoading = false,
                                isError = true
                            )
                        }
                    }
                } catch (e: Exception) {
                    println("Error from repo")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
            } else {
                val Currencies = currencyRepository.getLocalCurrencies()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        currencyList = Currencies
                    )
                }
            }
        }

    }

    private fun getCurrencyProfile(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isError = false
                )
            }

            if (!_state.value.isOffline) {
                try {
                    val currency = currencyRepository.getCurrencyProfileFromNetwork(id)
                    if (currency.id != "") {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                currency = currency
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isError = true
                            )
                        }
                    }

                } catch (e: Exception) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
            } else {
                val currency = currencyRepository.getLocalCurrency(id)
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        currency = currency
                    )
                }
            }
        }
    }

    private fun makeOffline() {
        viewModelScope.launch {
            currencyRepository.populateLocalCurrencyDatabase()
        }
        if (!_state.value.isError) {
            _state.update {
                it.copy(
                    isOffline = true
                )
            }
        } else {
            _state.update {
                it.copy(
                    isOffline = false,
                    isError = true
                )
            }
        }


    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = RoomDependencies.provideDatabase(application)
                val api = KtorCoinCapApi(KtorDependencies.provideHttpClient())
                CurrencyViewModel(
                    currencyRepository = LocalCurrencyRepository(
                        currencyDao = db.currencyDAO(),
                        api = api
                    )
                )
            }

        }
    }



}