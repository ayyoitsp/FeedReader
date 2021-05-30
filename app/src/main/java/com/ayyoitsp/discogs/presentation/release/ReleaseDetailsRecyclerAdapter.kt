package com.ayyoitsp.discogs.presentation.release

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.ReleaseDetails
import com.ayyoitsp.discogs.domain.model.ReleaseTrack
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.header_release_details.view.*
import kotlinx.android.synthetic.main.item_release_track.view.*

class ReleaseDetailsRecyclerAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var releaseDetails: ReleaseDetails? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            HEADER_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_release_details, parent, false)
                HeaderViewHolder(view)
            }
            TRACK_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_release_track, parent, false)
                TrackViewHolder(view)
            }
            else -> throw RuntimeException("Unknown view type $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        releaseDetails?.let {
            when (position) {
                0 -> (holder as? HeaderViewHolder)?.bind(it)
                else -> (holder as? TrackViewHolder)?.bind(it.trackList[position - 1])
            }
        }

    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0) HEADER_TYPE else TRACK_TYPE

    override fun getItemCount(): Int = releaseDetails?.let { it.trackList.size + 1 } ?: 0

    companion object {
        const val HEADER_TYPE = 0
        const val TRACK_TYPE = 1
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(releaseDetails: ReleaseDetails) = with(itemView) {
            imageLoader.loadImageIntoView(
                releaseDetails.imageUrl,
                releaseImageView,
                R.drawable.placeholder_cover
            )
            titleTextView.text = "${releaseDetails.title} - ${releaseDetails.year}"
            artistNameTextView.text = String.format(
                resources.getString(R.string.artists_format),
                releaseDetails.artistNames.joinToString(", ")
            )
            if (releaseDetails.notes.isEmpty()) {
                notesTextView.visibility = View.GONE
            } else {
                notesTextView.visibility = View.VISIBLE
                notesTextView.text = releaseDetails.notes
            }
        }
    }

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(track: ReleaseTrack) = with(itemView) {
            trackPositionTextView.text = track.position
            trackTitleTextView.text = track.title
            durationTextView.text = track.duration
        }
    }
}
