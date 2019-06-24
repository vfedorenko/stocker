package com.itechart.stocker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.itechart.stocker.R
import com.itechart.stocker.Stock
import com.itechart.stocker.databinding.ItemStockBinding
import kotlinx.android.synthetic.main.item_stock.view.*

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

        holder.binding.root.apply {
            setOnClickListener {
                val transitionNames = arrayOf(
                    "chartView$position",
                    "tickerText$position",
                    "valueText$position"
                )

                chartView.transitionName = transitionNames[0]
                tickerText.transitionName = transitionNames[1]
                valueText.transitionName = transitionNames[2]

                val extras = FragmentNavigatorExtras(
                    chartView to transitionNames[0],
                    tickerText to transitionNames[1],
                    valueText to transitionNames[2]
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
