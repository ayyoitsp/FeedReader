/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.domain.model.ReleaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtistReleasesUseCaseImpl(private val discoRepository: DiscoRepository) :
    GetArtistReleasesUseCase {
    override fun execute(artistId: String): Flow<List<ReleaseModel>> =
        flow {
            emit(discoRepository.getArtistReleases(artistId))
        }
}