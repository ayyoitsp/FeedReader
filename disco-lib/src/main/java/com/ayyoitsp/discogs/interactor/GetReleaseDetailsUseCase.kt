/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import kotlinx.coroutines.flow.Flow

/**
 * UseCase for getting [ReleaseDetails]
 */
interface GetReleaseDetailsUseCase {
    fun execute(artistId: String): Flow<ReleaseDetails>
}