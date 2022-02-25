package com.my.currency.view.currencylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.my.currency.model.db.CurrencyDatabase
import com.my.currency.model.db.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CurrencyListViewModel @ViewModelInject constructor(
    private val currencyDatabase: CurrencyDatabase
) : ViewModel() {
    private val _readCurrencyInfoResult = MutableLiveData<CurrencyInfo>()
    val readCurrencyInfoResult: LiveData<CurrencyInfo> = _readCurrencyInfoResult

    fun readCurrencyInfosById(id: String) {
        viewModelScope.launch {
            flow{
                val list = currencyDatabase.currencyInfoDao().getCurrencyInfoByID(id)
                emit(
                    list
                )
            }.flowOn(Dispatchers.IO)
                .collect {
                    _readCurrencyInfoResult.value = it
                }
        }
    }
}