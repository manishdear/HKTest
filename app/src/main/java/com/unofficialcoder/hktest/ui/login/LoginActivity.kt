package com.unofficialcoder.hktest.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.unofficialcoder.hktest.HKTestApplication

import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.di.ActivityModule
import com.unofficialcoder.hktest.di.DaggerActivityComponent
import com.unofficialcoder.hktest.ui.home.HomeActivity
import com.unofficialcoder.hktest.utils.Status
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupView()
        setupObservables()
    }

    fun setupObservables(){

        loginViewModel.emailField.observe(this, Observer {
            if (username.text.toString() != it) username.setText(it)
        })

        loginViewModel.emailValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> layout_email.error = it.data?.run { getString(this) }
                else -> layout_email.isErrorEnabled = false
            }
        })

        loginViewModel.passwordField.observe(this, Observer {
            if (password.text.toString() != it) password.setText(it)
        })

        loginViewModel.passwordValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> layout_password.error = it.data?.run { getString(this) }
                else -> layout_password.isErrorEnabled = false
            }
        })

        loginViewModel.loggingIn.observe(this, Observer {
            loading.visibility = if (it) View.VISIBLE else View.GONE
        })

        loginViewModel.launchHome.observe(this, Observer {
            startActivity( Intent(this, HomeActivity::class.java))
        })

    }

    fun setupView(){
        username.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.onEmailChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loginViewModel.onPasswordChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })


        login.setOnClickListener{
            //startActivity( Intent(applicationContext, HomeActivity::class.java))
            loginViewModel.onLogin()
        }

        tv_sign_up.setOnClickListener {
            Toast.makeText(applicationContext, "Sorry not Available please try login", Toast.LENGTH_SHORT).show()
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

    override fun onBackPressed() {
        super.onBackPressed()
        super.finish()
    }

}
