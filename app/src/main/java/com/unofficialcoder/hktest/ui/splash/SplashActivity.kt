package com.unofficialcoder.hktest.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.unofficialcoder.hktest.HKTestApplication
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.di.ActivityComponent
import com.unofficialcoder.hktest.di.ActivityModule
import com.unofficialcoder.hktest.di.DaggerActivityComponent
import com.unofficialcoder.hktest.ui.base.BaseActivity
import com.unofficialcoder.hktest.ui.home.HomeActivity
import com.unofficialcoder.hktest.ui.login.LoginActivity
import com.unofficialcoder.hktest.utils.Event
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.onCreate()

        setupObservers()
    }


    fun setupObservers() {

        viewModel.launchLogin.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        })

        viewModel.launchDummy.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
            }
        })
    }

    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as HKTestApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    override fun onResume() {
        super.onResume()
        super.finish()
    }
}