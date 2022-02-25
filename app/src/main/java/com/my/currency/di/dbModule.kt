package com.my.currency.di

import android.content.Context
import androidx.room.Room
import com.my.currency.model.db.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object dbModule {

    @Singleton
    @Provides
    fun providePhotosDatabase(@ApplicationContext context: Context): CurrencyDatabase {
        val db = Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java, "currency.db"
        ).build()
        return db
    }
}
