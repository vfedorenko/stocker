package com.itechart.stocker.businesslogic

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface StockApi {

    // TODO Api key should be hided
    @Headers(
        "X-RapidAPI-Host: investors-exchange-iex-trading.p.rapidapi.com",
        "X-RapidAPI-Key: 99e9afde6emsh6dc3bda0c1290c8p10a1a4jsn833afac86e5b"
    )
    @GET("stock/{ticker}/time-series")
    suspend fun getStockInfo(@Path("ticker") ticker: String): Response<List<StockValue>>
}
