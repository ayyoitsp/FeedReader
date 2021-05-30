/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayyoitsp.discogs.navigation.NavigationEvent

/**
 * Common base ViewModel used to abstract out common components
 *
 * Navigation events need to be cleared, otherwise re-showing a fragment
 * using a ViewModel would trigger the event to be fired again.  Could have used
 * SingleLiveEvent, but that does not feel good.
 */
abstract class BaseViewModel :ViewModel() {

    /**
     * Navigation events for observers to handle
     */
    val navigationEvents = MutableLiveData<NavigationEvent?>()

    /**
     * Notify ViewModel that the navigation event was handled
     */
    fun onNavigationConsumed() {
        navigationEvents.value = null
    }


}