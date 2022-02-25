package com.my.currency

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.my.currency.model.db.CurrencyInfo
import com.my.currency.utils.Util
import com.my.currency.view.currencylist.CurrencyListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_demo.*

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {
    private val viewModel: DemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        //input dataset
        val inputDataSet = Util.getJsonFromAssets(this, "currencyList.json")
        inputDataSet?.let{ insertCurrency(it) }

        setupUI()
        setupObserver()
        setupListener()

        //move to fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrencyListFragment.newInstance())
                .commit()
        }
    }

    private fun setupListener() {
        bt_load.setOnClickListener { viewModel.readAllCurrencyInfos() }
        bt_sort.setOnClickListener { viewModel.readAllSortedCurrencyInfosByName() }
    }

    private fun setupUI() {
    }

    fun setupObserver() {
    }

    private fun insertCurrency(dataset: String) {
        val currencyList = Gson().fromJson(dataset, Array<CurrencyInfo>::class.java).toList()
        viewModel.insertCurrencyInfos(currencyList)
    }

}