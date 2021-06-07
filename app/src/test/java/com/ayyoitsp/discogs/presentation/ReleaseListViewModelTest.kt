/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation

import androidx.lifecycle.Observer
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.domain.interactor.GetArtistReleasesUseCase
import com.ayyoitsp.discogs.navigation.NavigationEvent
import com.ayyoitsp.discogs.presentation.artistrelease.ReleaseListViewModel
import com.ayyoitsp.discogs.presentation.artistrelease.ReleaseListViewState
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class ReleaseListViewModelTest : BaseViewModelTest() {

    private val getArtistReleasesUseCase: GetArtistReleasesUseCase = mock()

    private val artist = Artist("1", "", "", "")

    private lateinit var viewModel: ReleaseListViewModel

    private lateinit var stateObserver: Observer<ReleaseListViewState>
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
        viewModel = ReleaseListViewModel(artist, getArtistReleasesUseCase)
        stateObserver = mock()
        navigationObserver = mock()
        viewModel.viewState.observeForever(stateObserver)
        viewModel.navigationEvents.observeForever(navigationObserver)
    }

    @Test
    fun `Ensure start state is valid on successful response`() {

        whenever(getArtistReleasesUseCase.execute(any()))
            .thenReturn(flowOf(emptyList()))

        postUseCaseSetup()

        // should be 3, but since observer isn't set until after things execute, really just 1
        verify(stateObserver, times(1)).onChanged(any())
        verify(getArtistReleasesUseCase).execute(artist.artistId)

        val state = viewModel.viewState.value

        assert(state != null)
        state?.let {
            assert(it.loading.not())
            assert(it.artist == artist)
            assert(it.releases.isEmpty())
            assert(it.errorType == null)
        }
    }

    @Test
    fun `Ensure start state is valid on server error`() {

        whenever(getArtistReleasesUseCase.execute(any()))
            .thenThrow(RuntimeException("error"))

        postUseCaseSetup()

        // should be 3, but since observer isn't set until after things execute, really just 1
        verify(stateObserver, times(1)).onChanged(any())

        val state = viewModel.viewState.value

        assert(state != null)
        state?.let {
            assert(it.loading.not())
            assert(it.artist == artist)
            assert(it.releases.isEmpty())
            assert(it.errorType != null)
        }
    }

    @Test
    fun `Ensure artist details navigation is handled`() {

        whenever(getArtistReleasesUseCase.execute(any()))
            .thenReturn(flowOf(emptyList()))

        postUseCaseSetup()

        viewModel.onArtistProfileSelected()

        val navigationEvent = viewModel.navigationEvents.value
        assert(navigationEvent is NavigationEvent.ArtistDetails)

        viewModel.onNavigationConsumed()
        assert(viewModel.navigationEvents.value == null)
    }

    @Test
    fun `Ensure release details navigation is handled`() {

        whenever(getArtistReleasesUseCase.execute(any()))
            .thenReturn(flowOf(emptyList()))

        postUseCaseSetup()

        viewModel.onReleaseSelected("")

        val navigationEvent = viewModel.navigationEvents.value
        assert(navigationEvent is NavigationEvent.ReleaseDetails)

        viewModel.onNavigationConsumed()
        assert(viewModel.navigationEvents.value == null)
    }

}