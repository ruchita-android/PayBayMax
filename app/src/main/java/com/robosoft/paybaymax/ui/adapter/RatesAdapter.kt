package com.robosoft.paybaymax.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.paybaymax.R
import com.robosoft.paybaymax.data.model.Quotes
import kotlinx.android.synthetic.main.item_list.view.*


class RatesAdapter(private val quotes: ArrayList<Quotes>, private  val currencyVal: String) : RecyclerView.Adapter<RatesAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quote: Quotes, currencyVal: String ) {
            itemView.apply {
                textCurrency.text = quote.currency
                textQuote.text = quote.rates.toString()
                textConvert.text = "Convert Value : "+(currencyVal.toDouble()*quote.rates).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(quotes[position], currencyVal)
    }

    fun addQuotes(quotes: List<Quotes>, currencyVal : String) {
        this.currencyVal.apply { currencyVal }
        this.quotes.apply {
            clear()
            addAll(quotes)
        }

    }


}