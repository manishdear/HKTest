package com.unofficialcoder.hktest.ui.home.photo

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
import com.unofficialcoder.hktest.data.model.Photo
import com.unofficialcoder.hktest.data.model.Post
import com.unofficialcoder.hktest.di.FragmentComponent
import com.unofficialcoder.hktest.ui.base.BaseFragment
import kotlinx.android.synthetic.main.photo_fragment.*
import org.json.JSONArray
import java.util.HashMap
import javax.inject.Inject


class PhotoFragment : BaseFragment<PhotoViewModel>() {

    companion object {

        const val TAG = "DummiesFragment"

        fun newInstance(): PhotoFragment {
            val args = Bundle()
            val fragment = PhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var requestQueue: RequestQueue
    lateinit var stringRequest: StringRequest

    var photoUrl = "https://jsonplaceholder.typicode.com/photos"

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var dummiesAdapter: PhotoAdapter

    override fun provideLayoutId(): Int = R.layout.photo_fragment

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        viewModel.getDummies().observe(this, Observer {
            dummiesAdapter.appendData(it)
        })

        viewModel.photoLoading.observe(this, Observer {
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
            StringRequest(Request.Method.GET, photoUrl, Response.Listener<String> {
                try {
                    val jsonArray: JSONArray = JSONArray(it)

                    var i = 0;

                    val list = ArrayList<Photo>()

                    while (i < jsonArray.length()) {
                        var currentPost = jsonArray.getJSONObject(i)

                        var mPost = Photo(currentPost.getInt("albumId"),
                            currentPost.getInt("id"),
                            currentPost.getString("title"),
                            currentPost.getString("url"),
                            currentPost.getString("thumbnailUrl"))

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