package com.uvg23088.laboratoriofinal.data.repo

import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.map
import com.uvg23088.laboratoriofinal.data.localdb.dao.CurrencyDAO
import com.uvg23088.laboratoriofinal.data.network.KtorCoinCapApi
import com.uvg23088.laboratoriofinal.domain.model.Currency
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result.Success
import com.uvg.ejercicioslabs.ejercicios.ktor.domain.network.util.Result.Error
import com.uvg23088.laboratoriofinal.data.network.dto.CurrencyDto
import com.uvg23088.laboratoriofinal.data.network.dto.mapToCurrencyModel
import com.uvg23088.laboratoriofinal.data.localdb.entity.mapToEntity
import com.uvg23088.laboratoriofinal.data.localdb.entity.CurrencyEntity
import com.uvg23088.laboratoriofinal.data.localdb.entity.mapToModel


class LocalCurrencyRepository(
    private val currencyDao: CurrencyDAO,
    private val api: KtorCoinCapApi
) {

    suspend fun getCurrenciesFromNetwork():List<Currency> {
        val result = api.getAllCurrencies().map{
            response -> response.data.map { it.mapToCurrencyModel() }
        }
        return when(result){
            is Success -> {
                result.data
            }
            is Error -> {
                println(result.error)
                emptyList()
            }
        }
    }

    suspend fun getCurrencyProfileFromNetwork(id: String): Currency {
        val result = api.getCurrencyProfile(id).map { it.data.mapToCurrencyModel() }
        return when(result) {
            is Success -> {
                result.data
            }
            is Error -> {
                Currency(
                    id = "",
                    name = "",
                    symbol = "",
                    priceUsd = "",
                    changePercent24Hr = "",
                    marketCapUsd = "",
                    maxSupply = "",
                    supply = ""
                )
            }
        }
    }

    suspend fun populateLocalCurrencyDatabase() {
        val result = api.getAllCurrencies().map{
                response -> response.data.map { it.mapToCurrencyModel() }
        }
        when(result){
            is Success -> {
                currencyDao.insertAll(result.data.map { it.mapToEntity() })
            }
            is Error -> {
                // Do nothing
            }
        }

    }

    suspend fun getLocalCurrencies(): List<Currency> {
        val LCurrency = currencyDao.getAllCurrencies()

        return LCurrency.map { localCurrency ->
            localCurrency.mapToModel()
        }
    }

    suspend fun getLocalCurrency(id: String): Currency {
        val localCurrency = currencyDao.getCurrency(id)

        return localCurrency.mapToModel()
    }



}