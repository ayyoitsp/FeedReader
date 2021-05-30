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
import com.ayyoitsp.discogs.presentation.BaseViewModel
import com.ayyoitsp.discogs.presentation.mapFetchError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for accessing all the [Release]s for an [Artist]
 *
 * The previously fetched [Artist] data must be provided.
 */
class ReleaseListViewModel(
    val artist: Artist,
    private val getReleaseListUseCase: GetArtistReleasesUseCase
) : BaseViewModel() {

    val viewState = MutableLiveData<ReleaseListViewState>()

    init {
        viewState.value = ReleaseListViewState(false, artist, emptyList())

        // Fetch artist release on init
        viewModelScope.launch {
            try {
                viewState.value = ReleaseListViewState(true, artist, emptyList())
                getReleaseListUseCase.execute(artist.artistId)
                    .collect {
                        viewState.value = ReleaseListViewState(false, artist, it)
                    }
            } catch (ex: Exception) {
                viewState.value = ReleaseListViewState(
                    false,
                    artist,
                    emptyList(),
                    mapFetchError(ex)
                )
            }
        }
    }

    /**
     * User selected the artist profile to view
     */
    fun onArtistProfileSelected() {
        navigationEvents.value = NavigationEvent.ArtistDetails(artist.artistId)
    }

    /**
     * User selected an album to view
     */
    fun onReleaseSelected(releaseId: String) {
        navigationEvents.value = NavigationEvent.ReleaseDetails(releaseId)
    }

}