/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.interactor.GetArtistDetailsUseCase
import com.ayyoitsp.discogs.presentation.ErrorType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArtistDetailsViewModel(
    private val artistId: String,
    private val getArtistDetailsUseCase: GetArtistDetailsUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ArtistDetailsViewState>()

    init {
        viewState.value = ArtistDetailsViewState(false, null, null)

        viewModelScope.launch {
            try {
                viewState.value = ArtistDetailsViewState(true, null, null)
                getArtistDetailsUseCase.execute(artistId)
                    .collect {
                        viewState.value = ArtistDetailsViewState(false, it, null)
                    }
            } catch (ex: Exception) {
                viewState.value = ArtistDetailsViewState(false, null, mapError(ex))
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