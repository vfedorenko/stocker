package com.itechart.stocker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel


class StockDetailsFragment : Fragment() {

    private lateinit var viewModel: StockDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stock_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StockDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

class StockDetailsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}

