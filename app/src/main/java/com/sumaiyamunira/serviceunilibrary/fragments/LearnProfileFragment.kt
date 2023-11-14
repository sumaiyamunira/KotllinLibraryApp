package com.sumaiyamunira.serviceunilibrary.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.activities.LearnLoginActivity
import com.sumaiyamunira.serviceunilibrary.databinding.LearnFragmentProfileBinding
import com.sumaiyamunira.serviceunilibrary.models.User
import com.sumaiyamunira.serviceunilibrary.utils.HelperClass
import com.sumaiyamunira.serviceunilibrary.utils.onClick

class LearnProfileFragment : Fragment() {
    private lateinit var binding: LearnFragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LearnFragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefUtils = ServiceUniLibraryApp.getSharedPrefUtils()
        if (sharedPrefUtils != null) {
            val userDataJson = sharedPrefUtils.getStringValue("user_data")

            if (userDataJson != null) {
                val retrievedUserData = User.fromJson(userDataJson)

                if (retrievedUserData != null) {
                    val email = retrievedUserData.username + "@examplemail.edu.au"
                    val fullname = retrievedUserData.fullname

                    binding.tvfullname.text = fullname
                    binding.tvemail.text = email

                    binding.idLogout.onClick {
                        if (sharedPrefUtils != null) {
                            val userDataJson = sharedPrefUtils.getStringValue("user_data")
                            if (userDataJson != null) {
                                val retrievedUserData = User.fromJson(userDataJson)
                                HelperClass.storeData(
                                    "Logout",
                                    retrievedUserData.fullname,
                                    "User"
                                )
                            }
                        }
                        sharedPrefUtils.removeKey("user_data")
                        finishActivity()
                    }
                    binding.llAchievements.onClick {
                    //    HelperClass.generateCSV()
                    }


                } else {
                    Log.d("ServiceUniLibrary", "situation 1 ")
                }
            } else {
                Log.d("ServiceUniLibrary", "situation 2 ")
            }
        } else {
            Log.d("ServiceUniLibrary", "situation 3 ")
        }
    }

    fun finishActivity() {
        val activity = activity
        val intent = Intent(activity, LearnLoginActivity::class.java)
        intent.putExtra("key", "value")
        startActivity(intent)

        if (activity != null) {
            activity.finish()
        }
    }
}