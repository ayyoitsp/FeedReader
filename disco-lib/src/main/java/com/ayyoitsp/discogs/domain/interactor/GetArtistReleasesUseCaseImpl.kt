/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.interactor

import com.ayyoitsp.discogs.domain.repository.DiscoRepository
import com.ayyoitsp.discogs.domain.model.Release
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArtistReleasesUseCaseImpl(private val discoRepository: DiscoRepository) :
    GetArtistReleasesUseCase {
    override fun execute(artistId: String): Flow<List<Release>> =
        flow {
            emit(discoRepository.getArtistReleases(artistId))
        }
}