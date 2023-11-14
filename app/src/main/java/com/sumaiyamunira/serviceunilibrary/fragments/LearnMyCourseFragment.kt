package com.sumaiyamunira.serviceunilibrary.fragments

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.adapter.BookListAdapter
import com.sumaiyamunira.serviceunilibrary.databinding.LearnFragmentMyBookBinding
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemBookRowBinding
import com.sumaiyamunira.serviceunilibrary.models.BookDTO
import com.sumaiyamunira.serviceunilibrary.utils.*

class LearnMyCourseFragment : Fragment() {
    private lateinit var binding: LearnFragmentMyBookBinding

    val mFeaturedAdapter = BookListAdapter(
        layoutBindingInflater = { layoutInflater, parent, attachToParent ->
            LearnItemBookRowBinding.inflate(layoutInflater, parent, attachToParent)
        },
        onItemClick = { featuredItem ->
        }
    )

    private var selected: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LearnFragmentMyBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAllCourses.apply {
            layoutManager = GridLayoutManager(activity, 2)
            setHasFixedSize(true)
            binding.rvAllCourses.adapter = mFeaturedAdapter
            addItemDecoration(LearnGridSpacingItemDecoration(2, 5, false))
            binding.rvAllCourses.rvItemAnimation()
        }
        mFeaturedAdapter.clearItems()

        mFeaturedAdapter.addItems(getAllBooks())

        binding.tvAll.onClick {
            setConstraintGravity(id, view)
            selected = id
            mFeaturedAdapter.updateItems(getAllBooks())

        }
        binding.tvOngoing.onClick {
            setConstraintGravity(id, view)
            selected = id
            mFeaturedAdapter.updateItems(getBooksBorrowed())

        }
        binding.tvCompleted.onClick {
            setConstraintGravity(id, view)
            selected = id
            mFeaturedAdapter.updateItems(getReturnedBooks())

        }
        selected = binding.tvAll.id

    }

    private fun setConstraintGravity(i: Int, view: View) {
        binding.tvAll.setTextColor(
            ContextCompat.getColor(
                ServiceUniLibraryApp.getAppInstance(),
                R.color.learn_textColorSecondary
            )
        )
        binding.tvOngoing.setTextColor(
            ContextCompat.getColor(
                ServiceUniLibraryApp.getAppInstance(),
                R.color.learn_textColorSecondary
            )
        )
        binding.tvCompleted.setTextColor(
            ContextCompat.getColor(
                ServiceUniLibraryApp.getAppInstance(),
                R.color.learn_textColorSecondary
            )
        )
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.tabCourses)
        view.findViewById<TextView>(i)
            .setTextColor(
                ContextCompat.getColor(
                    ServiceUniLibraryApp.getAppInstance(),
                    R.color.learn_colorPrimary
                )
            )
        constraintSet.connect(
            R.id.tab,
            ConstraintSet.START,
            i,
            ConstraintSet.START,
            0
        )
        constraintSet.connect(
            R.id.tab,
            ConstraintSet.END,
            i,
            ConstraintSet.END,
            0
        )

        constraintSet.connect(
            R.id.tab,
            ConstraintSet.TOP,
            i,
            ConstraintSet.BOTTOM,
            0
        )
        TransitionManager.beginDelayedTransition(binding.tabCourses)
        constraintSet.applyTo(binding.tabCourses)
        binding.rvAllCourses.rvItemAnimation()
    }


    private fun getCourses(): List<BookDTO> {
        var list = ArrayList<BookDTO>()
        var featured = BookDTO()
        featured.img = R.drawable.book1
        featured.name = "Computer Programming"

        var featured2 = BookDTO()
        featured2.img = R.drawable.data_science
        featured2.name = "Data Science"

        var featured5 = BookDTO()
        featured5.img = R.drawable.deep_thinking
        featured5.name = "Deep Thinking"


        var featured6 = BookDTO()
        featured6.img = R.drawable.neural_network_
        featured6.name = "Neural Network"


        var featured3 = BookDTO()
        featured3.img = R.drawable.learn_walk_3
        featured3.name = "Modern Medicines"

        var featured4 = BookDTO()
        featured4.img = R.drawable.learn_walk_1
        featured4.name = "Project Management"



        list.add(featured)
        list.add(featured2)
        list.add(featured5)
        list.add(featured6)
        list.add(featured3)
        list.add(featured4)

        return list
    }


    private fun getBooksBorrowed(): List<BookDTO> {
        var list = ArrayList<BookDTO>()
        var featured = BookDTO()
        featured.img = R.drawable.book1
        featured.name = "Last Hope"

        var featured2 = BookDTO()
        featured2.img = R.drawable.book2
        featured2.name = "The One Who Flew The Cloud"

        var featured5 = BookDTO()
        featured5.img = R.drawable.book3
        featured5.name = "The Wind Of Change"


        var featured6 = BookDTO()
        featured6.img = R.drawable.book5
        featured6.name = "The Lean Startup"


        var featured3 = BookDTO()
        featured3.img = R.drawable.book7
        featured3.name = "Dead In The Water"

        var featured4 = BookDTO()
        featured4.img = R.drawable.book9
        featured4.name = "Financial Accountings For Manager"


        var featured44 = BookDTO()
        featured44.img = R.drawable.book10
        featured44.name = "I Will Teach You To Be Rich"


        list.add(featured)
        list.add(featured2)
        list.add(featured5)
        list.add(featured6)
        list.add(featured3)
        list.add(featured4)
        list.add(featured44)
        return list
    }


    private fun getAllBooks(): List<BookDTO> {
        var list = ArrayList<BookDTO>()
        var featured = BookDTO()
        featured.img = R.drawable.book1
        featured.name = "Last Hope"

        var featured2 = BookDTO()
        featured2.img = R.drawable.book2
        featured2.name = "The One Who Flew The Cloud"

        var featured5 = BookDTO()
        featured5.img = R.drawable.book3
        featured5.name = "The Wind Of Change"


        var featured6 = BookDTO()
        featured6.img = R.drawable.book5
        featured6.name = "The Lean Startup"


        var featured3 = BookDTO()
        featured3.img = R.drawable.book7
        featured3.name = "Dead In The Water"

        var featured4 = BookDTO()
        featured4.img = R.drawable.book9
        featured4.name = "Financial Accountings For Manager"


        var featured44 = BookDTO()
        featured44.img = R.drawable.book10
        featured44.name = "I Will Teach You To Be Rich"


        var featured11 = BookDTO()
        featured11.img = R.drawable.passion_of_giants
        featured11.name = "Passion Of Giants"


        var featured12 = BookDTO()
        featured12.img = R.drawable.a_million_to_one
        featured12.name = "A Million To One"






        list.add(featured)
        list.add(featured2)
        list.add(featured5)
        list.add(featured6)
        list.add(featured3)
        list.add(featured4)
        list.add(featured44)

        list.add(featured11)
        list.add(featured12)
        return list
    }

    private fun getReturnedBooks(): List<BookDTO> {
        var list = ArrayList<BookDTO>()


        var featured11 = BookDTO()
        featured11.img = R.drawable.passion_of_giants
        featured11.name = "Passion Of Giants"


        var featured12 = BookDTO()
        featured12.img = R.drawable.a_million_to_one
        featured12.name = "A Million To One"

        var featured13 = BookDTO()
        featured13.img = R.drawable.data_science
        featured13.name = "Data Science"

        var featured14 = BookDTO()
        featured14.img = R.drawable.deep_thinking
        featured14.name = "Deep Thinking"


        list.add(featured11)
        list.add(featured12)
        list.add(featured13)
        list.add(featured14)
        return list
    }


}