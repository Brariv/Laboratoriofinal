package com.uvg23088.laboratoriofinal.data.network.dto

import com.uvg23088.laboratoriofinal.data.localdb.entity.CurrencyEntity
import com.uvg23088.laboratoriofinal.domain.model.Currency
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDto(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val vwap24Hr: String,
)

fun CurrencyDto.mapToCurrencyModel(): Currency {
    return Currency(
        id = id,
        symbol = symbol,
        name = name,
        supply = supply?:"",
        maxSupply = maxSupply?:"",
        marketCapUsd = marketCapUsd?:"",
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CurrencyDto.mapToCurrencyEntity(): CurrencyEntity {
    return CurrencyEntity(
        id = id,
        symbol = symbol,
        name = name,
        supply = supply?:"",
        maxSupply = maxSupply?:"",
        marketCapUsd = marketCapUsd?:"",
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

