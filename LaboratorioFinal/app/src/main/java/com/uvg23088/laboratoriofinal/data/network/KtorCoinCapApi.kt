package com.uvg23088.laboratoriofinal.data.network

import com.uvg.ejercicioslabs.ejercicios.ktor.data.network.util.safeCall
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.NetworkError
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result
import com.uvg23088.laboratoriofinal.data.network.dto.CurrencyListDto
import com.uvg23088.laboratoriofinal.data.network.dto.CurrencyProfileDto
import com.uvg23088.laboratoriofinal.domain.network.CoinCapApi
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorCoinCapApi (
    private val httpClient: HttpClient
): CoinCapApi {
    override suspend fun getAllCurrencies(): Result<CurrencyListDto, NetworkError> {
        return safeCall<CurrencyListDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets"
            )

        }
    }


    override suspend fun getCurrencyProfile(id: String): Result<CurrencyProfileDto, NetworkError> {
        return safeCall<CurrencyProfileDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets/{id}"
            )
        }
    }
}