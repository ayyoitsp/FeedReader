/*
 * Copyright © 2021 Peter Hsu. All rights reserved.
 */
package com.ayyoitsp.discogs.presentation.utils

import android.widget.ImageView

interface ImageLoader {
    fun loadImageIntoView(path: String, imageView: ImageView, defaultImageResourceId: Int = 0)
}