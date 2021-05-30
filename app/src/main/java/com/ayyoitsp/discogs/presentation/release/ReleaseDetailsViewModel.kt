/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.release

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayyoitsp.discogs.interactor.GetReleaseDetailsUseCase
import com.ayyoitsp.discogs.presentation.mapError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReleaseDetailsViewModel(
    private val releaseId: String,
    private val getReleaseDetailsUseCase: GetReleaseDetailsUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ReleaseDetailsViewState>()

    init {
        viewState.value = ReleaseDetailsViewState(false, null, null)

        viewModelScope.launch {
            try {
                viewState.value = ReleaseDetailsViewState(true, null, null)
                getReleaseDetailsUseCase.execute(releaseId)
                    .collect {
                        viewState.value = ReleaseDetailsViewState(false, it, null)
                    }
            } catch (ex: Exception) {
                ex.printStackTrace()
                viewState.value = ReleaseDetailsViewState(false, null, mapError(ex))
            }
        }
    }

}