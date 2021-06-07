/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.domain.interactor.GetArtistDetailsUseCase
import com.ayyoitsp.discogs.presentation.BaseViewModel
import com.ayyoitsp.discogs.presentation.mapFetchError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for accessing [ArtistDetails] of a given artist.
 *
 * The artistId identifier must be specified to fetch data.
 */
class ArtistDetailsViewModel(
    private val artistId: String,
    private val getArtistDetailsUseCase: GetArtistDetailsUseCase
) : BaseViewModel() {

    val viewState = MutableLiveData<ArtistDetailsViewState>()

    init {
        viewState.value = ArtistDetailsViewState(false, null, null)

        // Fetch the artist details on init
        viewModelScope.launch {
            try {
                viewState.value = ArtistDetailsViewState(true, null, null)
                getArtistDetailsUseCase.execute(artistId)
                    .collect {
                        viewState.value = ArtistDetailsViewState(false, it, null)
                    }
            } catch (ex: Exception) {
                ex.printStackTrace()
                viewState.value = ArtistDetailsViewState(false, null, mapFetchError(ex))
            }
        }
    }

}