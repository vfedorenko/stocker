package com.itechart.stocker


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.navArgs


class PortfolioFragment : Fragment() {

    private lateinit var viewModel: PortfolioViewModel

    private val args: PortfolioFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        args.tickers.forEach { Log.d("1111", it) }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PortfolioViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

class PortfolioViewModel : ViewModel() {

    private val _navigateToStockDetails = MutableLiveData<Event<NavDirections>>()
    val navigateToStockDetails: LiveData<Event<NavDirections>>
        get() = _navigateToStockDetails
}
