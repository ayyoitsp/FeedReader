/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.domain.interactor

import com.ayyoitsp.discogs.domain.model.Release
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.domain.model.SearchResponse
import kotlinx.coroutines.flow.Flow

/**
 * UseCase for searching for [Release]s
 */
interface GetReleaseSearchResultsUseCase {
    fun execute(searchRequest: SearchRequest): Flow<SearchResponse<Release>>
}