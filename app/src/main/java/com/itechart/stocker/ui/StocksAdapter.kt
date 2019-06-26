package com.itechart.stocker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.itechart.stocker.R
import com.itechart.stocker.businesslogic.Stock
import com.itechart.stocker.databinding.ItemStockBinding
import kotlinx.android.synthetic.main.item_stock.view.chartView
import kotlinx.android.synthetic.main.item_stock.view.tickerText
import kotlinx.android.synthetic.main.item_stock.view.valueText

class StocksAdapter : RecyclerView.Adapter<BindingHolder>() {

    private val items = arrayListOf<Stock>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<ItemStockBinding>(
            LayoutInflater.from(parent.context), R.layout.item_stock, parent, false
        )
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = items[position]

        holder.bind(StockItemViewModel(item))

        val transitionNames = arrayOf(
            "chartView$position",
            "tickerText$position",
            "valueText$position",
            "root$position"
        )

        holder.binding.chartView.transitionName = transitionNames[0]
        holder.binding.tickerText.transitionName = transitionNames[1]
        holder.binding.valueText.transitionName = transitionNames[2]
        holder.binding.root.transitionName = transitionNames[3]

        holder.binding.root.apply {
            setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    chartView to transitionNames[0],
                    tickerText to transitionNames[1],
                    valueText to transitionNames[2],
                    this to transitionNames[3]
                )

                findNavController().navigate(PortfolioFragmentDirections.toStockDetails(item, transitionNames), extras)
            }
        }
    }

    fun updateItems(newItems: List<Stock>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class BindingHolder(val binding: ItemStockBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(itemViewModel: StockItemViewModel) {
        binding.viewModel = itemViewModel
        binding.executePendingBindings()
    }
}
