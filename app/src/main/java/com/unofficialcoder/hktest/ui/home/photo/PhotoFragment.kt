package com.unofficialcoder.hktest.ui.home.photo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.di.FragmentComponent
import com.unofficialcoder.hktest.ui.base.BaseFragment
import kotlinx.android.synthetic.main.photo_fragment.*
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
    }

}