package com.unofficialcoder.hktest.ui.home.post

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.ui.base.BaseAdapter


class PostAdapter(
    parentLifecycle: Lifecycle,
    postList : ArrayList<Post>
) : BaseAdapter<Post, PostItemViewHolder>(
    parentLifecycle, postList
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostItemViewHolder(parent)
}
