/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.interactor.GetArtistSearchResultsUseCase
import com.ayyoitsp.discogs.presentation.ErrorType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getArtistSearchResultsUseCase: GetArtistSearchResultsUseCase
) : ViewModel() {

    val viewState = MutableLiveData<SearchViewState>()

    var searchJob: Job? = null
    var searching = false
    var searchText = ""

    init {
        viewState.value = SearchViewState(false, emptyList(), false)
    }

    fun updateSearchText(text: String) {
        searchText = text
    }

    fun searchSelected() {
        startSearch(searchText)
    }

    private fun startSearch(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                searching = true
                viewState.value = SearchViewState(true, emptyList(), false)
                getArtistSearchResultsUseCase.execute(
                    SearchRequest(query, FIRST_PAGE, MAX_SEARCH_RESULTS)
                )
                    .collect {
                        searching = false
                        viewState.value = SearchViewState(false, it.artists, it.artists.isEmpty())

                    }
            } catch (ex: Exception) {
                searching = false
                viewState.value = SearchViewState(false, emptyList(), false, mapError(ex))
            }
        }
    }

    companion object {
        const val FIRST_PAGE = 1
        const val MAX_SEARCH_RESULTS = 50
        private fun mapError(ex: Exception): ErrorType {

            // TODO: map real errors
            return ErrorType.Network
        }
    }

}