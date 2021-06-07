/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation

import androidx.lifecycle.Observer
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.domain.model.SearchResponse
import com.ayyoitsp.discogs.domain.interactor.GetArtistSearchResultsUseCase
import com.ayyoitsp.discogs.navigation.NavigationEvent
import com.ayyoitsp.discogs.presentation.search.SearchViewModel
import com.ayyoitsp.discogs.presentation.search.SearchViewModel.Companion.FIRST_PAGE
import com.ayyoitsp.discogs.presentation.search.SearchViewModel.Companion.MAX_SEARCH_RESULTS
import com.ayyoitsp.discogs.presentation.search.SearchViewState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class SearchViewModelTest : BaseViewModelTest() {

    private val getArtistSearchResultsUseCase: GetArtistSearchResultsUseCase = mock()

    private lateinit var viewModel: SearchViewModel

    private lateinit var stateObserver: Observer<SearchViewState>
    private lateinit var navigationObserver: Observer<NavigationEvent?>

    @Before
    fun setup() {
        super.setupExecutor()
        viewModel = SearchViewModel(getArtistSearchResultsUseCase)
        stateObserver = mock()
        navigationObserver = mock()
        viewModel.viewState.observeForever(stateObserver)
        viewModel.navigationEvents.observeForever(navigationObserver)
    }


    @After
    fun tearDown() {
        super.teardownExecutor()
    }

    @Test
    fun `Ensure start state is valid`() {
        val state = viewModel.viewState.value

        assert(state != null)
        state?.let {
            assert(it.loading.not())
            assert(it.searchResults.isEmpty())
            assert(it.showNoResults.not())
            assert(it.errorType == null)
        }
    }

    @Test
    fun `Ensure search is started properly`() {

        val query = "query"
        viewModel.updateSearchText(query)

        val searchResponse = SearchResponse<Artist>(1, 1, 1, emptyList())

        runBlockingTest {
            whenever(getArtistSearchResultsUseCase.execute(any())).thenReturn(
                flowOf(searchResponse)
            )
            viewModel.searchSelected()

            // initial, loading, then complete state
            verify(stateObserver, times(3)).onChanged(any())
            verify(getArtistSearchResultsUseCase).execute(
                SearchRequest(query, FIRST_PAGE, MAX_SEARCH_RESULTS)
            )

            val state = viewModel.viewState.value
            assert(state != null)
            state?.let {
                // verify empty results
                assert(state.loading.not())
                assert(state.showNoResults)
                assert(state.searchResults.isEmpty())
                assert(state.errorType == null)
            }
        }

    }

    @Test
    fun `Ensure search error is handled properly`() {

        val query = "query"
        viewModel.updateSearchText(query)

        val searchResponse = SearchResponse<Artist>(1, 1, 1, emptyList())

        runBlockingTest {
            whenever(getArtistSearchResultsUseCase.execute(any()))
                .thenThrow(RuntimeException("error"))
            viewModel.searchSelected()

            // initial, loading, then error state
            verify(stateObserver, times(3)).onChanged(any())

            val state = viewModel.viewState.value
            assert(state != null)
            state?.let {
                // verify empty results
                assert(state.loading.not())
                assert(state.showNoResults.not())
                assert(state.searchResults.isEmpty())
                assert(state.errorType != null)
            }
        }
    }

    @Test
    fun `Ensure artist selection navigation is handled`() {

        val artist = Artist("1", "", "", "")
        viewModel.onArtistSelected(artist)

        val navigationEvent = viewModel.navigationEvents.value
        assert(navigationEvent is NavigationEvent.ArtistReleases)

        viewModel.onNavigationConsumed()
        assert(viewModel.navigationEvents.value == null)
    }
}