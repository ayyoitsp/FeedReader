/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.interactor

import com.ayyoitsp.discogs.domain.repository.DiscoRepository
import com.ayyoitsp.discogs.domain.model.ArtistDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtistDetailsUseCaseImpl(private val discoRepository: DiscoRepository) :
    GetArtistDetailsUseCase {
    override fun execute(artistId: String): Flow<ArtistDetails> =
        flow {
            emit(discoRepository.getArtistDetails(artistId))
        }
}