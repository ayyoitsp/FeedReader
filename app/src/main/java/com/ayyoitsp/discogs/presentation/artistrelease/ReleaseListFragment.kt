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
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import com.ayyoitsp.discogs.presentation.utils.ViewUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.release_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReleaseListFragment : Fragment() {
    val viewModel: ReleaseListViewModel by viewModel {
        parametersOf(requireArguments().getSerializable(KEY_ARTIST))
    }

    private val viewUtils: ViewUtils by inject()
    private val imageLoader: ImageLoader by inject()

    lateinit var releaseAdapter: ReleaseListRecyclerAdapter

    companion object {
        const val KEY_ARTIST = "artist"

        fun newInstance(artist: Artist) = ReleaseListFragment().apply {
            arguments = bundleOf(KEY_ARTIST to artist)
        }
    }

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

            imageLoader.loadImageIntoView(artist.coverImageUrl, artistImageView, R.drawable.placeholder_cover)
            imageLoader.loadImageIntoView(artist.thumbUrl, artistThumbnailImageView, R.drawable.placeholder_avatar)
            artistNameTextView.text = artist.displayName
        }
    }

    private fun bindViewModel() {
        val statusObserver = Observer<ReleaseListViewState> { renderViewState(it) }
        viewModel.viewState.observe(viewLifecycleOwner, statusObserver)
    }
}