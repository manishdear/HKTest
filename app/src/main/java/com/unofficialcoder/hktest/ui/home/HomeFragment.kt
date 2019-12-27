package com.unofficialcoder.hktest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.unofficialcoder.hktest.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return  inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager = view.findViewById(R.id.viewpager) as ViewPager

        val catagoryAdapter: HomeAdapter
        catagoryAdapter = HomeAdapter(childFragmentManager, context)

        viewPager.setAdapter(catagoryAdapter)

        val tabLayout: TabLayout = view.findViewById(R.id.tabs) as TabLayout

        tabLayout.setupWithViewPager(viewPager)
    }
}