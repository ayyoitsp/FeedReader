/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation

import androidx.lifecycle.Observer
import com.ayyoitsp.discogs.domain.model.ArtistDetails
import com.ayyoitsp.discogs.domain.interactor.GetArtistDetailsUseCase
import com.ayyoitsp.discogs.navigation.NavigationEvent
import com.ayyoitsp.discogs.presentation.artist.ArtistDetailsViewModel
import com.ayyoitsp.discogs.presentation.artist.ArtistDetailsViewState
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class AristDetailsViewModelTest : BaseViewModelTest() {

    private val getArtistDetailsUseCase: GetArtistDetailsUseCase = mock()

    private val artistId = "1"
    private val artistDetails = ArtistDetails("", "", "", "", emptyList())

    private lateinit var viewModel: ArtistDetailsViewModel

    private lateinit var stateObserver: Observer<ArtistDetailsViewState>
    private lateinit var navigationObserver: Observer<NavigationEvent?>

    @Before
    fun setup() {
        super.setupExecutor()
    }

    @After
    fun tearDown() {
        super.teardownExecutor()
    }

    private fun postUseCaseSetup() {
        viewModel = ArtistDetailsViewModel(artistId, getArtistDetailsUseCase)
        stateObserver = mock()
        viewModel.viewState.observeForever(stateObserver)
        viewModel.navigationEvents.observeForever(mock())
    }

    @Test
    fun `Ensure start state is valid on successful response`() {

        whenever(getArtistDetailsUseCase.execute(any()))
            .thenReturn(flowOf(artistDetails))

        postUseCaseSetup()

        // should be 3, but since observer isn't set until after things execute, really just 1
        verify(stateObserver, times(1)).onChanged(any())
        verify(getArtistDetailsUseCase).execute(artistId)

        val state = viewModel.viewState.value

        assert(state != null)
        state?.let {
            assert(it.loading.not())
            assert(it.artistDetails == artistDetails)
            assert(it.errorType == null)
        }
    }

    @Test
    fun `Ensure start state is valid on server error`() {

        whenever(getArtistDetailsUseCase.execute(any()))
            .thenThrow(RuntimeException("error"))

        postUseCaseSetup()

        // should be 3, but since observer isn't set until after things execute, really just 1
        verify(stateObserver, times(1)).onChanged(any())

        val state = viewModel.viewState.value

        assert(state != null)
        state?.let {
            assert(it.loading.not())
            assert(it.artistDetails == null)
            assert(it.errorType != null)
        }
    }
}

