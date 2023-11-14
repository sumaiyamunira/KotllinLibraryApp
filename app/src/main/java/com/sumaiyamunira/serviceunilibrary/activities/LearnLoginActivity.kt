package com.sumaiyamunira.serviceunilibrary.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.base.LearnerBaseActivity
import com.sumaiyamunira.serviceunilibrary.databinding.LearnActivityLoginBinding
import com.sumaiyamunira.serviceunilibrary.models.User
import com.sumaiyamunira.serviceunilibrary.utils.HelperClass
import com.sumaiyamunira.serviceunilibrary.utils.LearnerAppSharedPrefUtils
import com.sumaiyamunira.serviceunilibrary.utils.launchActivity
import com.sumaiyamunira.serviceunilibrary.utils.onClick

class LearnLoginActivity : LearnerBaseActivity() {
    private lateinit var binding: LearnActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LearnActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setStatusBar(this, R.color.learn_gradient_grey_1)

        binding.ivBack.onClick {
            finish()
        }
        binding.btnEnter.onClick {
            if (validateFields()) {

                val sharedPrefUtils = ServiceUniLibraryApp.getSharedPrefUtils()
                val userData = HelperClass.getUserDTO(binding.edtTxtUsername.text.toString())
                if (userData != null) {
                    if (sharedPrefUtils != null) {
                        HelperClass.storeData("Login", userData.fullname, "Authentication Requester")
                        Log.d("ServiceUniLibrary", "" + userData.toJson())
                        sharedPrefUtils.setValue("user_data", userData.toJson())
                        launchActivity<LearnerDashboardActivity>()
                        finish()
                    }
                } else {
                    Toast.makeText(
                        ServiceUniLibraryApp.getAppInstance(),
                        "User not found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
            }

        }

        binding.tvForget.onClick {
            Toast.makeText(
                ServiceUniLibraryApp.getAppInstance(),
                "Coming Soon...",
                Toast.LENGTH_SHORT
            ).show()


        }
    }


    // Function to perform validation
    fun validateFields(): Boolean {

        // Check if username is empty
        if (TextUtils.isEmpty(binding.edtTxtUsername.text.toString().trim())) {
            binding.edtTxtUsername.error = "Username is required"
            return false
        }

        // Check if password is empty
        if (TextUtils.isEmpty(binding.edtTxtPassword.text.toString().trim())) {
            binding.edtTxtPassword.error = "Password is required"
            return false
        }

        if (!HelperClass.isUsernameExists(binding.edtTxtUsername.text.toString())) {
            binding.edtTxtUsername.error = "Wrong username"
            return false
        }

        val enteredPassword = binding.edtTxtPassword.text.toString().trim()
        if (enteredPassword != "123456") {
            binding.edtTxtPassword.error = "Wrong password"
            return false
        }

        return true
    }
}
