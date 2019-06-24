package com.itechart.stocker

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itechart.stocker.ui.ChartView
import com.itechart.stocker.ui.StocksAdapter

@BindingAdapter("adapterItems")
fun setAdapterItems(recyclerView: RecyclerView, stockItems: List<Stock>) {
    val adapter = recyclerView.adapter as StocksAdapter
    adapter.updateItems(stockItems)
}

@BindingAdapter("chartValues")
fun setChartValues(chartView: ChartView, values: List<Float>) {
    chartView.valuesToDraw(values)
}
