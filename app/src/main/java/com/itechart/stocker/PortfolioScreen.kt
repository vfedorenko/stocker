package com.itechart.stocker


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.nitrico.lastadapter.LastAdapter
import com.itechart.stocker.databinding.FragmentPortfolioBinding
import com.itechart.stocker.databinding.FragmentTickerBinding
import org.koin.core.KoinComponent
import org.koin.core.inject


class PortfolioFragment : Fragment() {

    private lateinit var portfolioViewModel: PortfolioViewModel

    private val args: PortfolioFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        portfolioViewModel = ViewModelProviders.of(this@PortfolioFragment).get(PortfolioViewModel::class.java)
        portfolioViewModel.navigateToDirection.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(it)
            }
        })

        return DataBindingUtil.inflate<FragmentPortfolioBinding>(
            inflater,
            R.layout.fragment_portfolio,
            container,
            false
        )
            .apply {
                viewModel = portfolioViewModel
                lifecycleOwner = this@PortfolioFragment
            }.root
    }

    override fun onStart() {
        super.onStart()

        portfolioViewModel.init(args.tickers)
    }
}

class PortfolioViewModel : ViewModel(), KoinComponent, Routable by LiveDataNavigation() {

//    private val stocksRepository: StocksRepository by inject()
    private val stocksRepository: StocksRepository = StubStockRepository()

    private val stockViewModels = ObservableArrayList<StockItemViewModel>()

    private val onStockSelected: (Stock) -> Unit = { navigateTo(PortfolioFragmentDirections.toStockDetails(it)) }

    val adapter: LastAdapter = LastAdapter(stockViewModels, BR.viewModel)
        .map<StockItemViewModel>(R.layout.item_stock)

    fun init(tickers: Array<String>) {
        stocksRepository.getStocksByTickers(tickers) { stocks ->
            stockViewModels.clear()
            stocks.forEach {
                stockViewModels.add(StockItemViewModel(it, onStockSelected))
            }
        }
    }
}
