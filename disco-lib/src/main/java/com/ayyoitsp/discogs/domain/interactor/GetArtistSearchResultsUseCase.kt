/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.interactor

import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.domain.model.SearchResponse
import kotlinx.coroutines.flow.Flow

/**
 * UseCase for searching for [Artist]
 */
interface GetArtistSearchResultsUseCase {
    fun execute(searchRequest: SearchRequest) : Flow<SearchResponse<Artist>>
}