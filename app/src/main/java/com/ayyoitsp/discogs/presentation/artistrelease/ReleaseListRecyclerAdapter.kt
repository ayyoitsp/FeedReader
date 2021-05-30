package com.ayyoitsp.discogs.presentation.artistrelease

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.domain.model.Release
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.item_release.view.*

class ReleaseListRecyclerAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<ReleaseListRecyclerAdapter.ReleaseViewHolder>() {

    var releases: List<Release> = emptyList()
    var releaseClickListener: ((releaseId: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_release, parent, false)
        return ReleaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReleaseViewHolder, position: Int) {
        holder.bind(releases[position])
    }

    override fun getItemCount(): Int = releases.count()

    inner class ReleaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(release: Release) = with(itemView) {
            setOnClickListener {
                releaseClickListener?.invoke(release.releaseId)
            }
            imageLoader.loadImageIntoView(release.thumbUrl, releaseThumbnailImageView, R.drawable.placeholder_release)
            releaseTitleTextView.text = release.title
            releaseYearTextView.text = release.year
        }
    }
}

