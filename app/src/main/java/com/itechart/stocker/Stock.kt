package com.itechart.stocker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Stock(val ticker: String, val values: List<StockValue>) : Parcelable

@Parcelize
data class StockValue(
    val date: String,
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float
) : Parcelable
