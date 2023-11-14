package com.sumaiyamunira.serviceunilibrary.base

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.utils.learnerChangeToolbarFont
import io.github.inflationx.viewpump.ViewPumpContextWrapper

open class LearnerBaseActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar(this)

    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    fun setToolbar(mToolbar: Toolbar) {
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mToolbar.setNavigationIcon(R.drawable.learn_ic_back)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
        mToolbar.learnerChangeToolbarFont()
    }

    fun setStatusBar(activity: Activity,color:Int=R.color.learn_white) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val window = activity.window
                var flags = activity.window.decorView.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                activity.window.decorView.systemUiVisibility = flags
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(this, color)
            }
            else -> {
                window.statusBarColor =  ContextCompat.getColor(this,R.color.learn_semi_white)
            }
        }
    }
}
