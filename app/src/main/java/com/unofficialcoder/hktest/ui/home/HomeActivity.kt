package com.unofficialcoder.hktest.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.unofficialcoder.hktest.HKTestApplication
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.di.ActivityModule
import com.unofficialcoder.hktest.di.DaggerActivityComponent
import com.unofficialcoder.hktest.ui.login.LoginActivity
import javax.inject.Inject


class HomeActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener{

    @Inject
    lateinit var viewModel: HomeViewModel

    private var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val headerView: View = navigationView.getHeaderView(0)
        val navUserName = headerView.findViewById<TextView>(R.id.username)
        val navEmail = headerView.findViewById<TextView>(R.id.emailid)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        val node = toggle
        if(node != null){
            drawerLayout?.addDrawerListener(node)
        }

        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                HomeFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_post)
        }

        viewModel.userName.observe(this, Observer {
            navUserName.text = it
        })

        viewModel.userEmail.observe(this, Observer {
            navEmail.text = it
        })

        viewModel.logout.observe(this, Observer {
            if (it) super.finish()
        })

        viewModel.onCreate()

        viewModel.onBack.observe(this, Observer {
            if(!it) super.finish()
        })

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_post ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    HomeFragment()
                ).commit()
            R.id.nav_logout -> viewModel.onLogout()

        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            viewModel.backPress()
        }
    }

    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as HKTestApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

}