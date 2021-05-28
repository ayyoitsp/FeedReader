/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artistrelease

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayyoitsp.discogs.interactor.GetReleaseSearchResultsUseCase

class ReleaseListViewModel(
    private val artistId: String,
    private val getReleaseSearchResultsUseCase: GetReleaseSearchResultsUseCase
) : ViewModel() {

    private val viewState = MutableLiveData<ReleaseListViewState>()

    init {
        viewState.value = ReleaseListViewState(false, emptyList(), null)
    }

}