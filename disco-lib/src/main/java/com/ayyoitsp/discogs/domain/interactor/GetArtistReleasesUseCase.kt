/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.domain.interactor

import com.ayyoitsp.discogs.domain.model.Release
import kotlinx.coroutines.flow.Flow

/**
 * UseCase for fetching [Release]s for a given artist
 */
interface GetArtistReleasesUseCase {
    fun execute(artistId: String): Flow<List<Release>>
}