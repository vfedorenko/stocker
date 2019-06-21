package com.itechart.stocker

class StockItemViewModel(
    val stock: Stock,
    private val onItemClick: (Stock) -> Unit
) {

    fun onItemClicked() {
        onItemClick.invoke(stock)
    }
}
