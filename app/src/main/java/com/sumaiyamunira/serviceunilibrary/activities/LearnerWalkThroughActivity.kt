package ccom.sumaiyamunira.serviceunilibrary.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.activities.LearnLoginActivity
import com.sumaiyamunira.serviceunilibrary.activities.LearnerDashboardActivity
import com.sumaiyamunira.serviceunilibrary.base.LearnerBaseActivity
import com.sumaiyamunira.serviceunilibrary.utils.launchActivity
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources
import com.sumaiyamunira.serviceunilibrary.utils.makeTransaprant
import com.sumaiyamunira.serviceunilibrary.utils.onClick
import com.sumaiyamunira.serviceunilibrary.databinding.LearnerActivityWalkThroughNewBinding
import com.sumaiyamunira.serviceunilibrary.databinding.LearnerItemWalkBinding


class LearnerWalkThroughActivity : LearnerBaseActivity() {
    var mHeading = arrayOf("Find Books", "Borrow Books", "Book Meeting Room")

    private  lateinit var binding: LearnerActivityWalkThroughNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LearnerActivityWalkThroughNewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        makeTransaprant()
        val adapter = WalkAdapter()
        binding.vpWalkthough.adapter =adapter
        //vpBoarding.adapter = adapter
        binding.btnGetStarted.onClick {
            launchActivity<LearnLoginActivity>()
            finish()

        }
        binding.tvLogin.onClick {
           // launchActivity<LearnLoginActivity>()
           // finish()
        }
        binding.vpWalkthough.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
            {
            }

            override fun onPageSelected(position: Int) {
                binding.tvHeading.text=mHeading[binding.vpWalkthough.realItem]
            }


        })
    }

    internal inner class WalkAdapter : PagerAdapter() {
        private val mImg = arrayOf(R.drawable.book,  R.drawable.library, R.drawable.conversation)

        override fun isViewFromObject(v: View, `object`: Any): Boolean {
            return v === `object` as View
        }

        override fun getCount(): Int {
            return mImg.size
        }

        override fun instantiateItem(parent: ViewGroup, position: Int): Any {
            val itemBinding = LearnerItemWalkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            // Now, you can access views within the item's layout using itemBinding
            itemBinding.imgWalk2.loadImageFromResources(applicationContext, mImg[position])
            parent.addView(itemBinding.root)
            return itemBinding.root
        }


        override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
            parent.removeView(`object` as View)
        }
    }

}
