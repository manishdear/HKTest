package com.unofficialcoder.hktest.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.ui.home.photo.PhotoFragment
import com.unofficialcoder.hktest.ui.home.post.PostFragment

class HomeAdapter( fragmentManager: FragmentManager, private val mContext: Context?) :FragmentPagerAdapter(fragmentManager){


    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            PhotoFragment()
        } else {
            PostFragment()
        }
    }

    /**
     * Return the total number of pages.
     */
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            mContext!!.getString(R.string.photos_tab)
        } else {
            mContext!!.getString(R.string.posts_tab)
        }
    }
}