package com.unofficialcoder.hktest.ui.home.post

import android.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.di.components.ViewHolderComponent
import com.unofficialcoder.hktest.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.post_list_view.view.*


class PostItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<Post, PostItemViewModel>(R.layout.post_list_view, parent) {

    var titlel = "title"
    var description = "Description"

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.title.observe(this, Observer {
            itemView.title.text = it
        })

        viewModel.description.observe(this, Observer {
            itemView.description.text = it
        })


        viewModel.onItemClick(adapterPosition)

    }

    override fun setupView(view: View) {

        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)

            val alertDialog =
                AlertDialog.Builder(view.context)
            alertDialog.setTitle(titlel)
            alertDialog.setMessage(description)

            val dialog = alertDialog.create()
            dialog.show()


        }
    }
}