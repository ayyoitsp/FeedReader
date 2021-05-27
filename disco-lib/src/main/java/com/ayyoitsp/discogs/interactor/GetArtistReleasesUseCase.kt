/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.domain.model.ReleaseModel
import kotlinx.coroutines.flow.Flow

interface GetArtistReleasesUseCase {
    fun execute(artistId: String): Flow<List<ReleaseModel>>
}