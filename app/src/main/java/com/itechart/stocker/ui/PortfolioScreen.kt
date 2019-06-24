package com.itechart.stocker.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.itechart.stocker.*
import com.itechart.stocker.databinding.FragmentPortfolioBinding
import org.koin.core.KoinComponent


class PortfolioFragment : Fragment() {

    private lateinit var portfolioViewModel: PortfolioViewModel

    private val args: PortfolioFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPortfolioBinding>(
            inflater, R.layout.fragment_portfolio, container, false
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = StocksAdapter()

        portfolioViewModel = ViewModelProviders.of(this@PortfolioFragment).get(PortfolioViewModel::class.java)

        binding.apply {
            viewModel = portfolioViewModel
            lifecycleOwner = this@PortfolioFragment
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        portfolioViewModel.init(args.tickers)
    }
}

class PortfolioViewModel : ViewModel(), KoinComponent, Routable by LiveDataNavigation() {

    //    private val stocksRepository: StocksRepository by inject()
    private val stocksRepository: StocksRepository = StubStockRepository()

    val stockItems = ObservableArrayList<Stock>()

    fun init(tickers: Array<String>) {
        stocksRepository.getStocksByTickers(tickers) { stocks ->
            stockItems.clear()
            stockItems.addAll(stocks)
        }
    }
}
