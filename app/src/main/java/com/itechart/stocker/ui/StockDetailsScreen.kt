package com.itechart.stocker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.itechart.stocker.R
import com.itechart.stocker.Stock
import com.itechart.stocker.databinding.FragmentStockDetailsBinding


class StockDetailsFragment : Fragment() {

    private lateinit var stockViewModel: StockDetailsViewModel

    private val args: StockDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStockDetailsBinding>(
            inflater, R.layout.fragment_stock_details, container, false
        )

        initAnimation(binding)

        stockViewModel = ViewModelProviders.of(this@StockDetailsFragment).get(StockDetailsViewModel::class.java)
        stockViewModel.initStock(args.stock)

        binding.apply {
            viewModel = stockViewModel
            lifecycleOwner = this@StockDetailsFragment
        }

        return binding.root
    }

    private fun initAnimation(binding: FragmentStockDetailsBinding) {
        sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.move)

        val transitions = args.transitionNames

        binding.chartView.transitionName = transitions[0]
        binding.tickerText.transitionName = transitions[1]
        binding.valueText.transitionName = transitions[2]
    }
}

class StockDetailsViewModel : ViewModel() {

    val ticker = ObservableField<String>()
    val lastValue = ObservableFloat()
    val chartValues = ObservableArrayList<Float>()

    private lateinit var stock: Stock

    fun initStock(stock: Stock) {
        this.stock = stock

        ticker.set(stock.ticker)
        lastValue.set(stock.values.last().close)
        chartValues.addAll(
            stock.values
                .map { it.close }
                .take(10)
        )
    }
}
