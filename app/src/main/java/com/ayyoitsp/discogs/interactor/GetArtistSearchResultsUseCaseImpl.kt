/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.domain.ArtistModel
import com.ayyoitsp.discogs.domain.SearchRequestModel
import com.ayyoitsp.discogs.domain.SearchResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtistSearchResultsUseCaseImpl(private val discoRepository: DiscoRepository) : GetArtistSearchResultsUseCase {
    override fun execute(searchRequestModel: SearchRequestModel): Flow<SearchResponseModel<ArtistModel>> =
        flow {
            emit(discoRepository.getArtistSearchResults(searchRequestModel))
        }
}