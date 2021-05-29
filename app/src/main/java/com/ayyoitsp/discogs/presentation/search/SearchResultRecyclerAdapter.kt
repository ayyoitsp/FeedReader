package com.ayyoitsp.discogs.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayyoitsp.discogs.R
import com.ayyoitsp.discogs.domain.model.Artist
import com.ayyoitsp.discogs.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.item_artist_search_result.view.*

class SearchResultRecyclerAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<SearchResultRecyclerAdapter.ArtistResultHolderView>() {

    var searchResults: List<Artist> = emptyList()
    var artistClickListener: ((artist: Artist) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultRecyclerAdapter.ArtistResultHolderView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist_search_result, parent, false)
        return ArtistResultHolderView(view)
    }

    override fun onBindViewHolder(holder: ArtistResultHolderView, position: Int) {
        holder.bind(searchResults[position])
    }

    override fun getItemCount(): Int = searchResults.count()

    inner class ArtistResultHolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(artist: Artist) = with(itemView) {
            setOnClickListener {
                artistClickListener?.invoke(artist)
            }

            imageLoader.loadImageIntoView(
                artist.coverImageUrl,
                coverImageView,
                R.drawable.placeholder_cover
            )
            imageLoader.loadImageIntoView(
                artist.thumbUrl,
                artistThumbnailImageView,
                R.drawable.placeholder_avatar
            )
            artistNameTextView.text = artist.displayName
        }
    }

}

