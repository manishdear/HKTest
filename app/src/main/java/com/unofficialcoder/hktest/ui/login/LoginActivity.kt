package com.unofficialcoder.hktest.ui.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.unofficialcoder.hktest.HKTestApplication
import com.unofficialcoder.hktest.R
import com.unofficialcoder.hktest.data.model.User
import com.unofficialcoder.hktest.di.ActivityModule
import com.unofficialcoder.hktest.di.DaggerActivityComponent
import com.unofficialcoder.hktest.ui.home.HomeActivity
import com.unofficialcoder.hktest.utils.Status
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private val INTERNET_PER = Manifest.permission.INTERNET
    private val NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE

    private var PERMISSION_REQUEST_CODE = 1234
    private var mLocationPermissionGranted = false

    lateinit var requestQueue: RequestQueue
    lateinit var stringRequest: StringRequest

    val loginUrl = "http://139.59.87.150/demo/Shree_Sai_Mall/public/api/user-login"

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


        login.setOnClickListener {

            if(loginViewModel.validate()){
                login()
            }
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

    private fun getInternetPermission() {
        Log.d("Login", "getLocationPermission: getting location permissions")
        val permission = arrayOf(Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE)
        if (ContextCompat.checkSelfPermission(this.applicationContext, INTERNET_PER) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.applicationContext,NETWORK_STATE) == PackageManager.PERMISSION_GRANTED ) {
                mLocationPermissionGranted = true
                login()
            } else {
                ActivityCompat.requestPermissions(this, permission,PERMISSION_REQUEST_CODE )
            }
        } else {
            ActivityCompat.requestPermissions(this, permission,PERMISSION_REQUEST_CODE )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        Log.d("OnResult", "onRequestPermissionsResult: called.")
        mLocationPermissionGranted = false
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0) {
                    var i = 0
                    while (i < grantResults.size) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false
                            Log.d(
                                "",
                                "onRequestPermissionsResult: permission failed")
                            return
                        }
                        i++
                    }
                    Log.d("Granted", "onRequestPermissionsResult: permission granted")
                    mLocationPermissionGranted = true
                    // initialize our map
                    login()
                }
            }
        }
    }

    private fun login(){
        //Setting of Network Request
        requestQueue = Volley.newRequestQueue(this@LoginActivity)

        loginViewModel.loginStart()

        val email = loginViewModel.emailField.value
        val password = loginViewModel.passwordField.value

        if (email != null && password != null) {

            stringRequest = object :
                StringRequest(Request.Method.POST, loginUrl, Response.Listener<String> {
                    try {
                        val jsonObject: JSONObject = JSONObject(it)
                        val dataJson = jsonObject.getJSONObject("data")
                        val name = dataJson.getString("name")
                        val userEmail = dataJson.getString("email")

                        val user = User(name, userEmail)

                        loginViewModel.saveCurrentUser(user)

                    } catch (e: java.lang.Exception) {

                    }
                }, Response.ErrorListener {
                    Log.i("VollyError", it.toString())
                }) {
                override fun getParams(): MutableMap<String, String> {
                    val params: MutableMap<String, String> = HashMap()

                    params.put("type", "a")
                    params.put("email", email)
                    params.put("password", password)
                    Log.e("templesList", "getParams: $params")

                    return params
                }
            }
            requestQueue.add(stringRequest)

        }
    }


}
