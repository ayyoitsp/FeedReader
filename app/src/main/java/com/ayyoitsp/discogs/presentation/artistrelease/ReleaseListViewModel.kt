/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artistrelease

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.interactor.GetArtistReleasesUseCase
import com.ayyoitsp.discogs.presentation.ErrorType
import com.ayyoitsp.discogs.presentation.artist.ArtistDetailsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReleaseListViewModel(
    val artist: Artist,
    private val getReleaseListUseCase: GetArtistReleasesUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ReleaseListViewState>()

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
                    mapError(ex)
                )
            }
        }

    }

    companion object {
        private fun mapError(ex: Exception): ErrorType {

            // TODO: map real errors
            return ErrorType.Network
        }
    }


}