package com.itechart.stocker.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.itechart.stocker.*
import com.itechart.stocker.databinding.FragmentTickerBinding
import java.util.regex.Pattern


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

        return DataBindingUtil.inflate<FragmentTickerBinding>(
            inflater, R.layout.fragment_ticker, container, false
        ).apply {
            viewModel = tickerViewModel
            lifecycleOwner = this@TickerFragment
        }.root
    }
}

class TickerViewModel : ViewModel(), Routable by LiveDataNavigation(),
    ErrorCleanerTextWatcher by errorCleanerTextWatcher {

    val tickerText = ObservableField<String>()

    val tikersErrorRes = ObservableInt()

    private val tikerPattern = Pattern.compile("^[A-Za-z]{1,5}$")

    fun onNextClick() {
        val tickers = parseTickers(tickerText.get())

        if (validateTickers(tickers)) {
            navigateTo(TickerFragmentDirections.navigateToPortfolio(tickers))
        }
    }

    private fun parseTickers(tickersString: String?): Array<String> =
        if (!tickersString.isNullOrEmpty()) {
            tickersString
                .split(",")
                .map { it.trim().toUpperCase() }
                .toTypedArray()
        } else {
            tikersErrorRes.set(R.string.error_empty_string)
            arrayOf()
        }

    private fun validateTickers(tickers: Array<String>): Boolean {
        var isAllValid = true

        if (tickers.isEmpty()) {
            isAllValid = false
        } else if (tickers.size > 5) {
            tikersErrorRes.set(R.string.error_too_many_tickers)
            isAllValid = false
        }

        tickers.forEach {
            if (!tikerPattern.matcher(it).matches()) {
                isAllValid = false
                tikersErrorRes.set(R.string.error_wrong_format)
            }
        }

        return isAllValid
    }
}
