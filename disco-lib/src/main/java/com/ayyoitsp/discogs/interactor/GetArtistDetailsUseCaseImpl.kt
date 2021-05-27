/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.domain.model.ArtistDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtistDetailsUseCaseImpl(private val discoRepository: DiscoRepository) : GetArtistDetailsUseCase {
    override fun execute(artistId: String): Flow<ArtistDetails> =
        flow {
            emit(discoRepository.getArtistDetails(artistId))
        }
}