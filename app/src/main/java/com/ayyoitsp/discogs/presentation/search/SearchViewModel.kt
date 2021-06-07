/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.domain.interactor.GetArtistSearchResultsUseCase
import com.ayyoitsp.discogs.navigation.NavigationEvent
import com.ayyoitsp.discogs.presentation.BaseViewModel
import com.ayyoitsp.discogs.presentation.mapFetchError
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for accessing search results of [Artist]s.
 *
 * For simplicity, paging is not implemented, and only the first n configured results
 * are fetched and displayed.  However, since
 */
class SearchViewModel(
    private val getArtistSearchResultsUseCase: GetArtistSearchResultsUseCase
) : BaseViewModel() {

    val viewState = MutableLiveData<SearchViewState>()

    // Internal state tracking
    private var searchJob: Job? = null
    private var searching = false
    private var searchText = ""

    init {
        Log.e("****", "$this initializing view state")
        viewState.value = SearchViewState(false, emptyList(), false)
    }

    /**
     * Update the data that user has entered into query input
     */
    fun updateSearchText(text: String) {
        searchText = text
    }

    /**
     * User has selected to start search
     */
    fun searchSelected() {
        startSearch(searchText)
    }

    /**
     * User selected a displayed [Artist] from search result
     */
    fun onArtistSelected(artist: Artist) {
        navigationEvents.value = NavigationEvent.ArtistReleases(artist)
    }

    @Synchronized
    private fun startSearch(query: String) {
        // cancel any outstanding jobs, new search overrides it
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                searching = true
                Log.e("****", "${this@SearchViewModel} searching view state")
                viewState.value = SearchViewState(true, emptyList(), false)
                getArtistSearchResultsUseCase.execute(
                    SearchRequest(query, FIRST_PAGE, MAX_SEARCH_RESULTS)
                )
                    .collect {
                        searching = false
                        Log.e("****", "${this@SearchViewModel} finished view state")
                        viewState.value = SearchViewState(false, it.results, it.results.isEmpty())

                    }
            } catch (ex: CancellationException) {
                // ignore job cancellations, we've canceled the search or been destroyed
            } catch (ex: Exception) {
                searching = false

                Log.e("****", "${this@SearchViewModel} exception view state")
                viewState.value = SearchViewState(false, emptyList(), false, mapFetchError(ex))
            }
        }
    }

    companion object {
        const val FIRST_PAGE = 1
        const val MAX_SEARCH_RESULTS = 50
    }

}