package com.uvg23088.laboratoriofinal.domain.network

import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.NetworkError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result
import com.uvg23088.laboratoriofinal.data.network.dto.CurrencyListDto
import com.uvg23088.laboratoriofinal.data.network.dto.CurrencyProfileDto

interface CoinCapApi {
    suspend fun getAllCurrencies(): Result<CurrencyListDto, NetworkError>
    suspend fun getCurrencyProfile(id: String): Result<CurrencyProfileDto, NetworkError>
}