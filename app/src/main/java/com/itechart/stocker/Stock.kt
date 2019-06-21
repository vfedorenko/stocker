package com.itechart.stocker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Stock(val ticker: String, val value: Float) : Parcelable
