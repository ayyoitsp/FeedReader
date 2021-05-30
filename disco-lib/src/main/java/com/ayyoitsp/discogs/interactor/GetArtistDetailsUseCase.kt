/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.domain.model.ArtistDetails
import kotlinx.coroutines.flow.Flow

/**
 * UseCase for fetching [ArtistDetails]
 */
interface GetArtistDetailsUseCase {
    fun execute(artistId: String): Flow<ArtistDetails>
}