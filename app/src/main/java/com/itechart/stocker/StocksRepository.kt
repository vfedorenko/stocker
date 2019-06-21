package com.itechart.stocker

import kotlin.random.Random

interface StocksRepository {
    fun getStocksByTickers(tickers: Array<String>, callback: (List<Stock>) -> Unit)
}

class StubStockRepository : StocksRepository {
    override fun getStocksByTickers(tickers: Array<String>,  callback: (List<Stock>) -> Unit) {
        callback.invoke(
            tickers.map { Stock(it, Random.nextDouble(50.0, 9999.0).toFloat()) }
        )
    }
}
