/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.release

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.domain.interactor.GetReleaseDetailsUseCase
import com.ayyoitsp.discogs.presentation.BaseViewModel
import com.ayyoitsp.discogs.presentation.mapFetchError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for accessing [ReleaseDetails] for a given release
 *
 * The releaseId must be supplied to fetch release details.
 */
class ReleaseDetailsViewModel(
    private val releaseId: String,
    private val getReleaseDetailsUseCase: GetReleaseDetailsUseCase
) : BaseViewModel() {

    val viewState = MutableLiveData<ReleaseDetailsViewState>()

    init {
        viewState.value = ReleaseDetailsViewState(false, null, null)

        // Fetch release details on init
        viewModelScope.launch {
            try {
                viewState.value = ReleaseDetailsViewState(true, null, null)
                getReleaseDetailsUseCase.execute(releaseId)
                    .collect {
                        viewState.value = ReleaseDetailsViewState(false, it, null)
                    }
            } catch (ex: Exception) {
                viewState.value = ReleaseDetailsViewState(false, null, mapFetchError(ex))
            }
        }
    }

}