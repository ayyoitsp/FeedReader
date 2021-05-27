/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.domain.ReleaseModel
import com.ayyoitsp.discogs.domain.SearchRequestModel
import com.ayyoitsp.discogs.domain.SearchResponseModel
import kotlinx.coroutines.flow.Flow

interface GetReleaseSearchResultsUseCase {
    fun execute(searchRequestModel: SearchRequestModel): Flow<SearchResponseModel<ReleaseModel>>
}