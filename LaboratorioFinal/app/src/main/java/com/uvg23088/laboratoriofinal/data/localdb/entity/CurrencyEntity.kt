package com.uvg23088.laboratoriofinal.data.localdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg23088.laboratoriofinal.domain.model.Currency

@Entity
data class CurrencyEntity (
    @PrimaryKey val id: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String,
    val marketCapUsd: String,
    val priceUsd: String,
    val changePercent24Hr: String
)

fun CurrencyEntity.mapToModel(): Currency {
    return Currency(
        id = id,
        symbol = symbol,
        name = name,
        supply = supply,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun Currency.mapToEntity(): CurrencyEntity {
    return CurrencyEntity(
        id = id,
        symbol = symbol,
        name = name,
        supply = supply,
         maxSupply= maxSupply?:"",
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}