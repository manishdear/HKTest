package com.unofficialcoder.hktest.ui.home.photo

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.ui.base.BaseAdapter


class PhotoAdapter(
    parentLifecycle: Lifecycle,
    postList : ArrayList<Photo>
) : BaseAdapter<Photo, PhotoItemViewHolder>(
    parentLifecycle, postList
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoItemViewHolder(parent)
}
