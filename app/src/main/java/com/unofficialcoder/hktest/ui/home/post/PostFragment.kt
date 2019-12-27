package com.unofficialcoder.hktest.ui.home.post

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.data.model.User
import com.unofficialcoder.hktest.di.FragmentComponent
import com.unofficialcoder.hktest.ui.base.BaseFragment
import kotlinx.android.synthetic.main.photo_fragment.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.HashMap
import javax.inject.Inject

class PostFragment : BaseFragment<PostViewModel>() {

    companion object {

        const val TAG = "DummiesFragment"

        fun newInstance(): PostFragment {
            val args = Bundle()
            val fragment = PostFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var requestQueue: RequestQueue
    lateinit var stringRequest: StringRequest

    var postUrl = "https://jsonplaceholder.typicode.com/posts"

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var dummiesAdapter: PostAdapter

    override fun provideLayoutId(): Int = R.layout.post_fragment

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {

        viewModel.getDummies().observe(this, Observer {
            dummiesAdapter.appendData(it)
        })

        viewModel.postLoading.observe(this, Observer {
            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun setupView(view: View) {
        rv_photo.layoutManager = linearLayoutManager
        rv_photo.adapter = dummiesAdapter

        fetchPost()
    }

    fun fetchPost(){
        //Setting of Network Request
        requestQueue = Volley.newRequestQueue(context)

        viewModel.postStatus(true)

        stringRequest = object :
            StringRequest(Request.Method.GET, postUrl, Response.Listener<String> {
                try {
                    val jsonArray: JSONArray = JSONArray(it)

                    var i = 0;

                    val list = ArrayList<Post>()

                    while (i < jsonArray.length()) {
                        var currentPost = jsonArray.getJSONObject(i)

                        var mPost = Post(currentPost.getInt("userId"),
                            currentPost.getInt("id"),
                            currentPost.getString("title"),
                            currentPost.getString("body"))

                        i++
                        list.add(mPost)

                    }

                    viewModel.postData(list)
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