/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.domain.interactor

import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import kotlinx.coroutines.flow.Flow

/**
 * UseCase for getting [ReleaseDetails]
 */
interface GetReleaseDetailsUseCase {
    fun execute(releaseId: String): Flow<ReleaseDetails>
}