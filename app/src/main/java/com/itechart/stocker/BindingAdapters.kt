package com.itechart.stocker

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itechart.stocker.ui.StocksAdapter

@BindingAdapter("adapterItems")
fun setAdapterItems(recyclerView: RecyclerView, stockItems: List<Stock>) {
    val adapter = recyclerView.adapter as StocksAdapter
    adapter.updateItems(stockItems)
}
