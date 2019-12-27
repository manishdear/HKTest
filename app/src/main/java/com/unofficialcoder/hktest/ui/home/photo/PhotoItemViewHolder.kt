package com.unofficialcoder.hktest.ui.home.photo

import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.di.components.ViewHolderComponent
import com.unofficialcoder.hktest.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.photo_list_view.view.*
import kotlinx.android.synthetic.main.post_list_view.view.*
import kotlinx.android.synthetic.main.post_list_view.view.description
import kotlinx.android.synthetic.main.post_list_view.view.title
import org.json.JSONObject
import java.util.HashMap

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

            lateinit var requestQueue: RequestQueue
            lateinit var stringRequest: StringRequest

            val position = adapterPosition+1

            var photoUrl = "https://jsonplaceholder.typicode.com/photos/"+position

            //Setting of Network Request
            requestQueue = Volley.newRequestQueue(view.context)

            viewModel.postStatus(true)

            stringRequest = object :
                StringRequest(Request.Method.GET, photoUrl, Response.Listener<String> {
                    try {
                        val currentPost: JSONObject = JSONObject(it)

                        var mPost = Photo(currentPost.getInt("albumId"),
                            currentPost.getInt("id"),
                            currentPost.getString("title"),
                            currentPost.getString("url"),
                            currentPost.getString("thumbnailUrl"))


                        val alertDialog =
                            AlertDialog.Builder(view.context)
                        alertDialog.setTitle(mPost.title)
                        alertDialog.setMessage(mPost.imageUrl)

                        val dialog = alertDialog.create()
                        dialog.show()

                        viewModel.postStatus(false)

                    } catch (e: java.lang.Exception) {

                    }
                }, Response.ErrorListener {
                    Log.i("VollyError", it.toString())
                    viewModel.postStatus(false)
                }) {
                override fun getParams(): MutableMap<String, String> {
                    val params: MutableMap<String, String> = HashMap()

                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
    }
}