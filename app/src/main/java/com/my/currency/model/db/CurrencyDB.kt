package com.my.currency.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyInfo::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {
    companion object {}

    abstract fun currencyInfoDao(): CurrencyInfoDao
}