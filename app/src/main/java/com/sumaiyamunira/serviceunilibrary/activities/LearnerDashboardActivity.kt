package com.sumaiyamunira.serviceunilibrary.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.base.LearnerBaseActivity
import com.sumaiyamunira.serviceunilibrary.fragments.*
import com.sumaiyamunira.serviceunilibrary.utils.replaceFragment
import com.sumaiyamunira.serviceunilibrary.databinding.LearnActivityDashboardBinding
import com.sumaiyamunira.serviceunilibrary.utils.HelperClass


class LearnerDashboardActivity : LearnerBaseActivity() {
    var mHomeFragment = LearnHomeFragment()
    var mSearchFragment = LearnSearchFragment()

    var mMyCourceFragment = LearnMyCourseFragment()
    var mMeetingRoom = BookMeetingRoomFragment()
    var mProfileFragment = LearnProfileFragment()
    var ids = arrayOf(R.id.navigation_home, R.id.navigation_search, R.id.navigation_statistical, R.id.navigation_meeting, R.id.navigation_more)

    private  lateinit var binding: LearnActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LearnActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setStatusBar(this,R.color.learn_layout_background)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            ids.forEachIndexed { index, i ->
                if (i == it.itemId) {
                    loadFragment(index)
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
        loadFragment(0)

    }

    private fun loadFragment(aFragment: Int) {
        replaceFragment(getItem(aFragment)!!, R.id.container)
    }
     fun getItem(position: Int): Fragment? {
         when (position) {
             0 -> return mHomeFragment
             1 -> return mSearchFragment
             2 -> return mMyCourceFragment
             3 -> return mMeetingRoom
             4 -> return mProfileFragment
         }
        return null
    }

}
