package com.my.currency

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.my.currency.model.db.CurrencyDatabase
import com.my.currency.model.db.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DemoViewModel @ViewModelInject constructor(
    private val currencyDatabase: CurrencyDatabase
) : ViewModel() {

    private val _readCurrencyInfosResult = MutableLiveData<List<CurrencyInfo>>()
    val readCurrencyInfosResult: LiveData<List<CurrencyInfo>> = _readCurrencyInfosResult

    fun insertCurrencyInfos(currencyInfos: List<CurrencyInfo>){
        viewModelScope.launch {
            currencyDatabase.withTransaction {
                currencyDatabase.currencyInfoDao().insertAll(*currencyInfos.toTypedArray())
            }
        }
    }

    fun readAllCurrencyInfos() {
        viewModelScope.launch {
            flow{
                val list = currencyDatabase.currencyInfoDao().getAll()
                emit(list)
            }.flowOn(Dispatchers.IO)
                .collect {
                    _readCurrencyInfosResult.value = it
                }
        }
    }

    fun readAllSortedCurrencyInfosByName() {
        viewModelScope.launch {
            flow{
                val list = currencyDatabase.currencyInfoDao().getAllSortedByName()
                emit(list)
            }.flowOn(Dispatchers.IO)
                .collect {
                    _readCurrencyInfosResult.value = it
                }
        }
    }

}