package com.itechart.stocker

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface StocksRepository {
    fun getStocksByTickers(tickers: Array<String>, callback: (List<Stock>) -> Unit)
}

class StocksRepositoryImpl(private val stockApi: StockApi) : StocksRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun getStocksByTickers(tickers: Array<String>, callback: (List<Stock>) -> Unit) {
        launch {
            val result = mutableListOf<Stock>()

            tickers.forEach {
                val values = stockApi.getStockInfo(it)

                if (!values.isNullOrEmpty()) {
                    result.add(Stock(it, values))
                }
            }

            callback.invoke(result)
        }
    }
}
