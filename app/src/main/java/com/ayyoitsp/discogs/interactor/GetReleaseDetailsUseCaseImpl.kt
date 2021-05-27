/*
 * Copyright © 2021 Peter Hsu All rights reserved.
 */
package com.ayyoitsp.discogs.interactor

import com.ayyoitsp.discogs.data.disco.DiscoRepository
import com.ayyoitsp.discogs.domain.ReleaseDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetReleaseDetailsUseCaseImpl(private val discoRepository: DiscoRepository) : GetReleaseDetailsUseCase {
    override fun execute(releaseId: String): Flow<ReleaseDetailsModel> =
        flow {
            emit(discoRepository.getReleaseDetails(releaseId))
        }
}