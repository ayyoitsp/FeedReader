/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.domain.interactor

import com.ayyoitsp.discogs.domain.repository.DiscoRepository
import com.ayyoitsp.discogs.domain.model.Release
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.domain.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetReleaseSearchResultsUseCaseImpl(private val discoRepository: DiscoRepository) :
    GetReleaseSearchResultsUseCase {
    override fun execute(searchRequest: SearchRequest): Flow<SearchResponse<Release>> =
        flow {
            emit(discoRepository.getReleaseSearchResults(searchRequest))
        }
}