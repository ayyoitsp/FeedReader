/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Picasso implementation of image loading
 */
class PicassoImageLoaderImpl : ImageLoader {
    override fun loadImageIntoView(
        path: String,
        imageView: ImageView,
        defaultImageResourceId: Int
    ) {
        imageView.setImageDrawable(null)
        try {
            Picasso.get().load(path).into(imageView, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: java.lang.Exception?) {
                    // Error loading image, use default image
                    imageView.setImageResource(defaultImageResourceId)
                }
            })
        } catch (ex: Exception) {
            // Error loading image (invalid URL), use default image
            imageView.setImageResource(defaultImageResourceId)
        }
    }
}