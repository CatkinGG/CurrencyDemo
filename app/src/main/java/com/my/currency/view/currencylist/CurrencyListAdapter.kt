package com.my.currency.view.currencylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.my.currency.R
import com.my.currency.model.db.CurrencyInfo
import com.my.currency.view.custom.ChatIconDrawable
import kotlinx.android.synthetic.main.item_currency.view.*


class CurrencyListAdapter(
    private val currencyListFuncItem: CurrencyListFuncItem
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data: MutableList<CurrencyInfo> = arrayListOf()

    companion object {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyListViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data.get(position)
        val context = holder.itemView.context
        holder as CurrencyListViewHolder
        holder.tvName.text = item.name
        holder.tvSymbol.text = item.symbol
        holder.tvSymbol.setOnClickListener { currencyListFuncItem.onCurrencySelect.invoke(item.id) }
        holder.ivId.run { this.setImageDrawable( ChatIconDrawable(this, item.id.first().toString())) }
        holder.ivQuestion.setOnClickListener { currencyListFuncItem.onCurrencySelect.invoke(item.id) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<CurrencyInfo>?) {
        this.data.clear()
        data?.also {
            this.data.addAll(it)
        }
        notifyDataSetChanged()
    }

    class CurrencyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivId: ImageView = itemView.iv_id
        val tvName: TextView = itemView.tv_name
        val tvSymbol: TextView = itemView.tv_symbol
        val ivQuestion: ImageView = itemView.iv_nav
    }
}