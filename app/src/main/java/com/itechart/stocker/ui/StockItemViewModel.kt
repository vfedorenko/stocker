package com.itechart.stocker.ui

import com.itechart.stocker.businesslogic.Stock

class StockItemViewModel(stock: Stock) {
    val ticker = stock.ticker
    val value = stock.values.last().close
    val chartValues = stock.values
        .map { it.close }
        .take(10)
}
