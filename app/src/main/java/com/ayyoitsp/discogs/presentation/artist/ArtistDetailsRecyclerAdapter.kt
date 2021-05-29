/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.ArtistDetails
import com.ayyoitsp.discogs.domain.model.ArtistMember
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.header_artist_details.view.*
import kotlinx.android.synthetic.main.item_artist_member.view.*


class ArtistDetailsRecyclerAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var artistDetails: ArtistDetails? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            HEADER_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_artist_details, parent, false)
                HeaderViewHolder(view)
            }
            MEMBER_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_artist_member, parent, false)
                MemberViewHolder(view)
            }
            else -> throw RuntimeException("Unknown view type $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        artistDetails?.let {
            when (position) {
                0 -> (holder as? HeaderViewHolder)?.bind(it)
                else -> (holder as? MemberViewHolder)?.bind(it.members[position - 1])
            }
        }

    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0) HEADER_TYPE else MEMBER_TYPE

    override fun getItemCount(): Int = artistDetails?.let { it.members.size + 1 } ?: 0

    companion object {
        const val HEADER_TYPE = 0
        const val MEMBER_TYPE = 1
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(artistDetails: ArtistDetails) = with(itemView) {
            imageLoader.loadImageIntoView(artistDetails.imageUrl, artistImageView, R.drawable.placeholder_cover)
            nameTextView.text = artistDetails.displayName
            profileTextView.text = artistDetails.profile
        }
    }

    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(member: ArtistMember) = with(itemView) {
            imageLoader.loadImageIntoView(member.thumbUrl, memberThumbnailImageView, R.drawable.placeholder_avatar)
            memberNameTextView.text = member.displayName
            memberActiveTextField.text =
                resources.getString(if (member.active) R.string.active else R.string.inactive)
        }
    }

}

