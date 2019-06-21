package com.itechart.stocker

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nitrico.lastadapter.LastAdapter

@BindingAdapter("lastAdapter")
fun setLastAdapter(recyclerView: RecyclerView, adapter: LastAdapter) {
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    adapter.into(recyclerView)
}
