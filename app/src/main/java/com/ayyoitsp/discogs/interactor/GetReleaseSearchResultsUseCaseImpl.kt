/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.domain.ReleaseModel
import com.ayyoitsp.discogs.domain.SearchRequestModel
import com.ayyoitsp.discogs.domain.SearchResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetReleaseSearchResultsUseCaseImpl(private val discoRepository: DiscoRepository) : GetReleaseSearchResultsUseCase {
    override fun execute(searchRequestModel: SearchRequestModel): Flow<SearchResponseModel<ReleaseModel>> =
        flow {
            emit(discoRepository.getReleaseSearchResults(searchRequestModel))
        }
}