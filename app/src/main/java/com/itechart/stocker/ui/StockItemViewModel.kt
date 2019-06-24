package com.itechart.stocker.ui

import com.itechart.stocker.Stock

class StockItemViewModel(private val stock: Stock) {
    val ticker = stock.ticker
    val value = stock.values.last().close
}
