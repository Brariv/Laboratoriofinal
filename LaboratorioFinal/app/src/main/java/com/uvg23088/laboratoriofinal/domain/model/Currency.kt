package com.uvg23088.laboratoriofinal.domain.model

data class Currency (
    val id: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String,
    val priceUsd: String,
    val changePercent24Hr: String
)