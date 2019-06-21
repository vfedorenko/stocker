package com.itechart.stocker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections

interface Routable {
    val navigateToDirection: LiveData<Event<NavDirections>>

    fun navigateTo(navDirections: NavDirections)
}

class LiveDataNavigation : Routable {

    private val _navigateToDirection = MutableLiveData<Event<NavDirections>>()

    override val navigateToDirection: LiveData<Event<NavDirections>>
        get() = _navigateToDirection


    override fun navigateTo(navDirections: NavDirections) {
        _navigateToDirection.value = Event(navDirections)
    }
}
