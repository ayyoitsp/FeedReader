/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.presentation.artistrelease.ReleaseListFragment
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import com.ayyoitsp.discogs.presentation.utils.ViewUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by inject()

    private val viewUtils: ViewUtils by inject()
    private val imageLoader: ImageLoader by inject()

    lateinit var searchResultAdapter: SearchResultRecyclerAdapter

    companion object {
        fun newInstance() = SearchFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchResultAdapter = SearchResultRecyclerAdapter(imageLoader)
        searchResultsRecyclerView.adapter = searchResultAdapter
        searchResultsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bindViewModel()
    }

    private fun renderViewState(searchViewState: SearchViewState) {
        with(searchViewState) {
            loadingSpinner.visibility = if (loading) View.VISIBLE else View.GONE
            errorType?.let {
                Snackbar.make(
                    requireView(),
                    viewUtils.mapErrorToStringResource(it),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            noResultsTextView.visibility = if (showNoResults) View.VISIBLE else View.GONE

            searchResultAdapter.searchResults = searchViewState.searchResults
            searchResultAdapter.notifyDataSetChanged()
        }
    }

    private fun bindViewModel() {
        val statusObserver = Observer<SearchViewState> { renderViewState(it) }
        viewModel.viewState.observe(viewLifecycleOwner, statusObserver)

        searchInput.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                event.keyCode == KeyEvent.KEYCODE_ENTER ||
                actionId == EditorInfo.IME_ACTION_DONE) {
                startSearch()
                true
            }
            false
        }
        searchInput.addTextChangedListener { viewModel.updateSearchText(it.toString()) }

        // TODO: MAKE THIS REACDTIVE
        searchResultAdapter.artistClickListener = object: SearchResultRecyclerAdapter.ArtistClickListener {
            override fun onArtistClicked(artist: Artist) {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .add(R.id.root_layout, ReleaseListFragment.newInstance(artist))
                    .addToBackStack("releases")
                    .commit()

            }
        }

    }

    private fun startSearch() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchInput.windowToken, 0)

        viewModel.searchSelected()
    }

}