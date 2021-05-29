/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artistrelease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.navigation.NavigationEvent
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import com.ayyoitsp.discogs.presentation.utils.ViewUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.release_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReleaseListFragment : Fragment() {
    private val viewModel: ReleaseListViewModel by viewModel {
        parametersOf(requireArguments().getSerializable(KEY_ARTIST))
    }
    private val viewUtils: ViewUtils by inject()
    private val imageLoader: ImageLoader by inject()

    lateinit var releaseAdapter: ReleaseListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.release_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        releaseAdapter = ReleaseListRecyclerAdapter(imageLoader)
        releaseListRecyclerView.adapter = releaseAdapter
        releaseListRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bindViewModel()
    }

    private fun renderViewState(releaseListViewState: ReleaseListViewState) {
        with(releaseListViewState) {
            loadingSpinner.visibility = if (loading) View.VISIBLE else View.GONE
            errorType?.let {
                Snackbar.make(
                    requireView(),
                    viewUtils.mapErrorToStringResource(it),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
            releaseAdapter.releases = releases
            releaseAdapter.notifyDataSetChanged()

            imageLoader.loadImageIntoView(
                artist.coverImageUrl,
                artistImageView,
                R.drawable.placeholder_cover
            )
            artistNameTextView.text = artist.displayName
        }
    }

    private fun handleNavigationEvent(navigationEvent: NavigationEvent?) {
        if (navigationEvent is NavigationEvent.ArtistDetails) {
            viewModel.onNavigationConsumed()

            val action =
                ReleaseListFragmentDirections.actionReleaseListFragmentToArtistDetailsFragment(
                    navigationEvent.artistId
                )
            findNavController().navigate(action)
        }
    }

    private fun bindViewModel() {
        val statusObserver = Observer<ReleaseListViewState> { renderViewState(it) }
        viewModel.viewState.observe(viewLifecycleOwner, statusObserver)

        val navObserver = Observer<NavigationEvent?> { handleNavigationEvent(it) }
        viewModel.navigationEvents.observe(viewLifecycleOwner, navObserver)

        profileButton.setOnClickListener { viewModel.onArtistProfileSelected() }
    }

    companion object {
        const val KEY_ARTIST = "artist"

        fun newInstance(artist: Artist) = ReleaseListFragment().apply {
            arguments = bundleOf(KEY_ARTIST to artist)
        }
    }
}