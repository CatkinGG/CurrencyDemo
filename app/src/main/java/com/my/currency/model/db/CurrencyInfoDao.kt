package com.my.currency.model.db

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity
data class CurrencyInfo(
    @SerializedName("id")
    @PrimaryKey
    val id: String,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("symbol")
    @ColumnInfo(name = "symbol")
    val symbol: String?
)

@Dao
interface CurrencyInfoDao {
    @Query("SELECT * FROM currencyinfo")
    fun getAll(): List<CurrencyInfo>

    @Query("SELECT * FROM currencyinfo ORDER BY name")
    fun getAllSortedByName(): List<CurrencyInfo>

    @Query("SELECT * FROM currencyinfo WHERE id IS (:id)")
    fun getCurrencyInfoByID(id: String): CurrencyInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg currencyInfo: CurrencyInfo)

    @Delete
    suspend fun delete(currencyInfo: CurrencyInfo)
}
