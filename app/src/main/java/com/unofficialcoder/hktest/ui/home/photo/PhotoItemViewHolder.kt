package com.unofficialcoder.hktest.ui.home.photo

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.di.components.ViewHolderComponent
import com.unofficialcoder.hktest.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.photo_list_view.view.*
import kotlinx.android.synthetic.main.post_list_view.view.*
import kotlinx.android.synthetic.main.post_list_view.view.description
import kotlinx.android.synthetic.main.post_list_view.view.title

class PhotoItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<Photo, PhotoItemViewModel>(R.layout.photo_list_view, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.title.observe(this, Observer {
            itemView.title.text = "Title Heading"
            itemView.description.text = it
        })

        viewModel.url.observe(this, Observer {
            Glide.with(itemView.context).load(it).into(itemView.image)
        })

    }

    override fun setupView(view: View) {
        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)
        }
    }
}