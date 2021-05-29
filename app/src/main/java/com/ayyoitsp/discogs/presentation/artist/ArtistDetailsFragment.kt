/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import com.ayyoitsp.discogs.presentation.utils.ViewUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.artist_details_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArtistDetailsFragment : Fragment() {
    private val viewModel: ArtistDetailsViewModel by viewModel {
        parametersOf(requireArguments().getString(KEY_ARTIST_ID))
    }
    private val viewUtils: ViewUtils by inject()
    private val imageLoader: ImageLoader by inject()

    lateinit var artistAdapter: ArtistDetailsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artist_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistAdapter = ArtistDetailsRecyclerAdapter(imageLoader)
        artistRecyclerView.adapter = artistAdapter
        artistRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bindViewModel()
    }

    private fun renderViewState(artistViewState: ArtistDetailsViewState) {
        with(artistViewState) {
            loadingSpinner.visibility = if (loading) View.VISIBLE else View.GONE
            errorType?.let {
                Snackbar.make(
                    requireView(),
                    viewUtils.mapErrorToStringResource(it),
                    Snackbar.LENGTH_LONG
                )
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

    companion object {
        const val KEY_ARTIST_ID = "artistId"

        fun newInstance(artistId: String) = ArtistDetailsFragment().apply {
            arguments = bundleOf(KEY_ARTIST_ID to artistId)
        }
    }
}