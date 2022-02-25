package com.my.currency.view.currencylist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.currency.DemoViewModel
import com.my.currency.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_currency_list.*
import timber.log.Timber


@AndroidEntryPoint
class CurrencyListFragment(
) : Fragment(R.layout.fragment_currency_list) {
    companion object {
        fun newInstance() = CurrencyListFragment()
    }

    private val currencyListFuncItem = CurrencyListFuncItem(
        onCurrencySelect = { id ->
            viewModel.readCurrencyInfosById(id)
        }
    )

    val currencyListAdapter = CurrencyListAdapter(currencyListFuncItem)
    private val viewModel: CurrencyListViewModel by viewModels()
    private val demoViewModel by activityViewModels<DemoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        setupListener()
        currencyListAdapter.setData(null)
        progress_bar.visibility = View.VISIBLE
    }

    fun setupUI() {
        rv_currency_list.also {
            it.setHasFixedSize(true)
            it.adapter = currencyListAdapter
            initDivider(requireContext(), it)
        }
    }

    fun setupObserver() {
        viewModel.readCurrencyInfoResult.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Get from db: $it",
                Toast.LENGTH_SHORT).show()
        })

        demoViewModel.readCurrencyInfosResult.observe(viewLifecycleOwner, {
            progress_bar.visibility = View.GONE
            currencyListAdapter.setData(it)
        })
    }

    fun setupListener() {
    }

    private fun initDivider(context: Context, recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )

        ContextCompat.getDrawable(context, R.drawable.currency_list_divider)
            ?.run {
                dividerItemDecoration.setDrawable(this)
                recyclerView.addItemDecoration(dividerItemDecoration)
            }
    }
}