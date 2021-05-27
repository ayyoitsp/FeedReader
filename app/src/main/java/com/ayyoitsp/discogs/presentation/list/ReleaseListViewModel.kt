/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.list

import androidx.lifecycle.ViewModel
import com.ayyoitsp.discogs.interactor.GetReleaseSearchResultsUseCase

class ReleaseListViewModel(
        private val getReleaseSearchResultsUseCase: GetReleaseSearchResultsUseCase
) : ViewModel() {
}