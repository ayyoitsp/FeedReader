/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artistrelease

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.interactor.GetArtistReleasesUseCase
import com.ayyoitsp.discogs.navigation.NavigationEvent
import com.ayyoitsp.discogs.presentation.mapFetchError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for accessing all the [Release]s for an [Artist]
 */
class ReleaseListViewModel(
    val artist: Artist,
    private val getReleaseListUseCase: GetArtistReleasesUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ReleaseListViewState>()
    val navigationEvents = MutableLiveData<NavigationEvent?>()

    init {
        viewState.value = ReleaseListViewState(false, artist, emptyList())

        viewModelScope.launch {
            try {
                viewState.value = ReleaseListViewState(true, artist, emptyList())
                getReleaseListUseCase.execute(artist.artistId)
                    .collect {
                        viewState.value = ReleaseListViewState(false, artist, it)
                    }
            } catch (ex: Exception) {
                ex.printStackTrace()
                viewState.value = ReleaseListViewState(
                    false,
                    artist,
                    emptyList(),
                    mapFetchError(ex)
                )
            }
        }

    }

    fun onArtistProfileSelected() {
        navigationEvents.value = NavigationEvent.ArtistDetails(artist.artistId)
    }

    fun onReleaseSelected(releaseId: String) {
        navigationEvents.value = NavigationEvent.ReleaseDetails(releaseId)
    }

    fun onNavigationConsumed() {
        navigationEvents.value = null
    }

}