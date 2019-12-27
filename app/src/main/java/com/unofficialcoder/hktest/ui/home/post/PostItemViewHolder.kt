package com.unofficialcoder.hktest.ui.home.post

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
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.di.components.ViewHolderComponent
import com.unofficialcoder.hktest.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.post_list_view.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap


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

        viewModel.loading.observe(this, Observer {
            itemView.loading.visibility =  if (it) View.VISIBLE else View.GONE
        })
    }

    override fun setupView(view: View) {

        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)

            lateinit var requestQueue: RequestQueue
            lateinit var stringRequest: StringRequest

            val position = adapterPosition+1

            var postUrl = "https://jsonplaceholder.typicode.com/posts/"+position

            //Setting of Network Request
            requestQueue = Volley.newRequestQueue(view.context)

            viewModel.postStatus(true)

            stringRequest = object :
                StringRequest(Request.Method.GET, postUrl, Response.Listener<String> {
                    try {
                        val currentPost: JSONObject = JSONObject(it)

                        var mPost = Post(currentPost.getInt("userId"),
                            currentPost.getInt("id"),
                            currentPost.getString("title"),
                            currentPost.getString("body"))


                        val alertDialog =
                            AlertDialog.Builder(view.context)
                        alertDialog.setTitle(mPost.title)
                        alertDialog.setMessage(mPost.body)

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