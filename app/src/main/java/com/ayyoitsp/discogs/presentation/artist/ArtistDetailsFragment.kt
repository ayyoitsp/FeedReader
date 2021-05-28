/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.presentation.ErrorType
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.artist_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArtistDetailsFragment : Fragment() {
    private val viewModel: ArtistDetailsViewModel by viewModel {
        parametersOf(requireArguments().getString(KEY_ARTIST_ID))
    }

    lateinit var artistAdapter: ArtistDetailsRecyclerAdapter

    companion object {
        const val KEY_ARTIST_ID = "artistId"

        fun newInstance(artistId: String) = ArtistDetailsFragment().apply {
            arguments = bundleOf(KEY_ARTIST_ID to artistId)
        }

        fun mapErrorToString(errorType: ErrorType): Int =
            when (errorType) {
                ErrorType.Network -> R.string.error_network
                ErrorType.NotFound -> R.string.error_not_found
                ErrorType.RateLimit -> R.string.error_rate_limit
                ErrorType.Unknown -> R.string.error_unknown
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artist_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistAdapter = ArtistDetailsRecyclerAdapter()
        artistRecyclerView.adapter = artistAdapter
        artistRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bindViewModel()
    }

    private fun renderViewState(artistViewState: ArtistDetailsViewState) {
        with(artistViewState) {
            loadingSpinner.visibility = if (loading) View.VISIBLE else View.GONE
            errorType?.let {
                Snackbar.make(requireView(), mapErrorToString(it), Snackbar.LENGTH_SHORT)
                    .show()
            }
            artistAdapter.artistDetails = artistDetails
            artistAdapter.notifyDataSetChanged()
        }
    }

    private fun bindViewModel() {
        val statusObserver = Observer<ArtistDetailsViewState> { renderViewState(it) }
        viewModel.viewState.observe(viewLifecycleOwner, statusObserver)
    }

}