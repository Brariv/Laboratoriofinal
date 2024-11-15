package com.uvg23088.laboratoriofinal.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyProfileDto (
    val data: CurrencyDto,
    val message: String,
    val status: Int
)