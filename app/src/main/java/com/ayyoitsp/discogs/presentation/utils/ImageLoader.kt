/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.utils

import android.widget.ImageView

/**
 * Generic interface for loading images, in case we want to switch out
 * the library being used for loading images.
 */
interface ImageLoader {
    fun loadImageIntoView(path: String, imageView: ImageView, defaultImageResourceId: Int = 0)
}