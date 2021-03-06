/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.release

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
import kotlinx.android.synthetic.main.release_details_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Fragment for displaying the details for a release.  Also displays the
 * tracks in a release.
 */
class ReleaseDetailsFragment : Fragment() {
    private val viewModel: ReleaseDetailsViewModel by viewModel {
        parametersOf(requireArguments().getString(KEY_RELEASE_ID))
    }
    private val viewUtils: ViewUtils by inject()
    private val imageLoader: ImageLoader by inject()

    lateinit var releaseAdapter: ReleaseDetailsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.release_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        releaseAdapter = ReleaseDetailsRecyclerAdapter(imageLoader)
        releaseRecyclerView.adapter = releaseAdapter
        releaseRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bindViewModel()
    }

    private fun renderViewState(releaseViewState: ReleaseDetailsViewState) {
        with(releaseViewState) {
            loadingSpinner.visibility = if (loading) View.VISIBLE else View.GONE
            errorType?.let {
                Snackbar.make(
                    requireView(),
                    viewUtils.mapErrorToStringResource(it),
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
            releaseAdapter.releaseDetails = releaseDetails
            releaseAdapter.notifyDataSetChanged()
        }
    }

    private fun bindViewModel() {
        val statusObserver = Observer<ReleaseDetailsViewState> { renderViewState(it) }
        viewModel.viewState.observe(viewLifecycleOwner, statusObserver)
    }

    companion object {
        const val KEY_RELEASE_ID = "releaseId"

        fun newInstance(releaseId: String) = ReleaseDetailsFragment().apply {
            arguments = bundleOf(KEY_RELEASE_ID to releaseId)
        }
    }
}