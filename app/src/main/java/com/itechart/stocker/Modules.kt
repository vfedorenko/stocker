package com.itechart.stocker

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<StocksRepository> {
        StocksRepositoryImpl(get())
    }

    single<StockApi> {
        get<Retrofit>().create(StockApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://investors-exchange-iex-trading.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
