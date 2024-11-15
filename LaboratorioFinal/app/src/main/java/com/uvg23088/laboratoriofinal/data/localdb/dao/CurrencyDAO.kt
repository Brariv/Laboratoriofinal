package com.uvg23088.laboratoriofinal.data.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uvg23088.laboratoriofinal.data.localdb.entity.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDAO {
    @Query("SELECT * FROM CurrencyEntity")
    suspend fun getAllCurrencies(): List<CurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<CurrencyEntity>)

    @Query ("SELECT * FROM CurrencyEntity WHERE id = :id")
    suspend fun getCurrency(id: String) : CurrencyEntity
}