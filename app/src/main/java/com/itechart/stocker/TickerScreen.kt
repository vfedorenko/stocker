package com.itechart.stocker


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.itechart.stocker.databinding.FragmentTickerBinding


class TickerFragment : Fragment() {

    private lateinit var tickerViewModel: TickerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tickerViewModel = ViewModelProviders.of(this@TickerFragment).get(TickerViewModel::class.java)
        tickerViewModel.navigateToDirection.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(it)
            }
        })

        return DataBindingUtil.inflate<FragmentTickerBinding>(inflater, R.layout.fragment_ticker, container, false)
            .apply {
                viewModel = tickerViewModel
                lifecycleOwner = this@TickerFragment
            }.root
    }
}

class TickerViewModel : ViewModel(), Routable by LiveDataNavigation() {

    val tickerText = ObservableField<String>()

    fun onNextClick() {
        val tickers = parseTickers(tickerText.get())

        if (tickers.isNotEmpty()) {
            navigateTo(TickerFragmentDirections.navigateToPortfolio(tickers))
        }

        // TODO Add max items filter
    }

    private fun parseTickers(tickersString: String?): Array<String> =
        if (!tickersString.isNullOrEmpty()) {
            tickersString
                .split(",")
                .map { it.trim().toUpperCase() }
                .toTypedArray()
        } else {
            //show error message
            arrayOf()
        }
}
