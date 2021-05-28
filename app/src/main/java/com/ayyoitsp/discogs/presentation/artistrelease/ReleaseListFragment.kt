/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artistrelease

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReleaseListFragment : Fragment() {
    val releaseListViewModel: ReleaseListViewModel by viewModel {
        parametersOf(requireArguments().getString(KEY_ARTIST_ID))
    }

    companion object {
        const val KEY_ARTIST_ID = "artistId"

        fun newInstance(artistId: String) = ReleaseListFragment().apply {
            arguments = bundleOf(KEY_ARTIST_ID to artistId)
        }
    }
}