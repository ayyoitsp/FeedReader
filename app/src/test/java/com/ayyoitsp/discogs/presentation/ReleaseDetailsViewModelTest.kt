/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation

import androidx.lifecycle.Observer
import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import com.ayyoitsp.discogs.domain.interactor.GetReleaseDetailsUseCase
import com.ayyoitsp.discogs.navigation.NavigationEvent
import com.ayyoitsp.discogs.presentation.release.ReleaseDetailsViewModel
import com.ayyoitsp.discogs.presentation.release.ReleaseDetailsViewState
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class ReleaseDetailsViewModelTest : BaseViewModelTest() {

    private val getReleaseDetailsUseCase: GetReleaseDetailsUseCase = mock()

    private val releaseId = "1"
    private val releaseDetails = ReleaseDetails("", "", "", emptyList(), "", "", emptyList())

    private lateinit var viewModel: ReleaseDetailsViewModel

    private lateinit var stateObserver: Observer<ReleaseDetailsViewState>
    private lateinit var navigationObserver: Observer<NavigationEvent?>

    @Before
    fun setup() {
        super.setupExecutor()
    }

    @After
    fun tearDown() {
        super.teardownExecutor()
    }

    private fun postUseCaseSetup() {
        viewModel = ReleaseDetailsViewModel(releaseId, getReleaseDetailsUseCase)
        stateObserver = mock()
        viewModel.viewState.observeForever(stateObserver)
        viewModel.navigationEvents.observeForever(mock())
    }

    @Test
    fun `Ensure start state is valid on successful response`() {

        whenever(getReleaseDetailsUseCase.execute(any()))
            .thenReturn(flowOf(releaseDetails))

        postUseCaseSetup()

        // should be 3, but since observer isn't set until after things execute, really just 1
        verify(stateObserver, times(1)).onChanged(any())
        verify(getReleaseDetailsUseCase).execute(releaseId)

        val state = viewModel.viewState.value

        assert(state != null)
        state?.let {
            assert(it.loading.not())
            assert(it.releaseDetails == releaseDetails)
            assert(it.errorType == null)
        }
    }

    @Test
    fun `Ensure start state is valid on server error`() {

        whenever(getReleaseDetailsUseCase.execute(any()))
            .thenThrow(RuntimeException("error"))

        postUseCaseSetup()

        // should be 3, but since observer isn't set until after things execute, really just 1
        verify(stateObserver, times(1)).onChanged(any())

        val state = viewModel.viewState.value

        assert(state != null)
        state?.let {
            assert(it.loading.not())
            assert(it.releaseDetails == null)
            assert(it.errorType != null)
        }
    }

}