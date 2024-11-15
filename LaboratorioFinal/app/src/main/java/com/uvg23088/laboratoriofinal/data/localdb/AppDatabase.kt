package com.uvg23088.laboratoriofinal.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uvg23088.laboratoriofinal.data.localdb.dao.CurrencyDAO
import com.uvg23088.laboratoriofinal.data.localdb.entity.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun currencyDAO(): CurrencyDAO
}