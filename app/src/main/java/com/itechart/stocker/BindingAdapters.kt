package com.itechart.stocker

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.itechart.stocker.businesslogic.Stock
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

@BindingAdapter("errorResId")
fun setError(textInputLayout: TextInputLayout, errorRes: Int) {
    val errorString = if (errorRes == 0) {
        null
    } else {
        textInputLayout.context.getString(errorRes)
    }

    textInputLayout.error = errorString
}
