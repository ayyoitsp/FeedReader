/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.domain.model.ReleaseModel
import com.ayyoitsp.discogs.domain.model.SearchRequest
import com.ayyoitsp.discogs.domain.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetReleaseSearchResultsUseCaseImpl(private val discoRepository: DiscoRepository) : GetReleaseSearchResultsUseCase {
    override fun execute(searchRequest: SearchRequest): Flow<SearchResponse<ReleaseModel>> =
        flow {
            emit(discoRepository.getReleaseSearchResults(searchRequest))
        }
}