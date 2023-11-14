package com.sumaiyamunira.serviceunilibrary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.adapter.HomeMainItemListAdapter
import com.sumaiyamunira.serviceunilibrary.adapter.LearnerRecyclerViewAdapter
import com.sumaiyamunira.serviceunilibrary.models.LearnCategory
import com.sumaiyamunira.serviceunilibrary.models.LearnFeatured
import com.sumaiyamunira.serviceunilibrary.utils.*
import com.sumaiyamunira.serviceunilibrary.databinding.LearnFragmentHomeBinding
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemFeaturedBinding
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemMainCategoryBinding


class LearnHomeFragment : Fragment() {
    private lateinit var binding: LearnFragmentHomeBinding

    val mFeaturedAdapter = LearnerRecyclerViewAdapter(
        layoutBindingInflater = { layoutInflater, parent, attachToParent ->
            LearnItemFeaturedBinding.inflate(layoutInflater, parent, attachToParent)
        },
        onItemClick = { featuredItem ->
        }
    )



    val mCategoryAdapter = HomeMainItemListAdapter(
        layoutBindingInflater = { layoutInflater, parent, attachToParent ->
            LearnItemMainCategoryBinding.inflate(layoutInflater, parent, attachToParent)
        },
        onItemClick = { featuredItem ->

        }
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LearnFragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFeatured.setHorizontalLayout()
        binding.rvFeatured.adapter = mFeaturedAdapter

        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            setHasFixedSize(true)
            adapter = mCategoryAdapter
            addItemDecoration(LearnGridSpacingItemDecoration(3, 30, false))
            rvItemAnimation()
        }

        mFeaturedAdapter.clearItems()
        mCategoryAdapter.clearItems()
        mFeaturedAdapter.addItems(getFeaturedItems())
        mCategoryAdapter.addItems(getCategories())
    }


    companion object {
        fun getCategories(): List<LearnCategory> {
            var list = ArrayList<LearnCategory>()
            var category = LearnCategory()
            category.img = R.drawable.learn_ic_bag
            category.name = "Business"

            var category1 = LearnCategory()
            category1.img = R.drawable.computer
            category1.name = "Computer"


            var category2 = LearnCategory()
            category2.img = R.drawable.learn_ic_pencil_scale
            category2.name = "Design"

            var category3 = LearnCategory()
            category3.img = R.drawable.learn_ic_crome
            category3.name = "Economy"

            var category4 = LearnCategory()
            category4.img = R.drawable.learn_ic_telecope
            category4.name = "Research"

            var category5 = LearnCategory()
            category5.img = R.drawable.learn_ic_medal
            category5.name = "Polytics"

            var category6 = LearnCategory()
            category6.img = R.drawable.learn_ic_crown
            category6.name = "Awards"

            var category7 = LearnCategory()
            category7.img = R.drawable.learn_ic_flag
            category7.name = "Sports"

            var category8 = LearnCategory()
            category8.img = R.drawable.learn_ic_cup
            category8.name = "Medals"

            list.add(category)
            list.add(category1)
            list.add(category2)
            list.add(category3)
            list.add(category4)
            list.add(category5)
            list.add(category6)
            list.add(category7)
            list.add(category8)
            return list

        }
    }


    private fun getFeaturedItems(): List<LearnFeatured> {
        var list = ArrayList<LearnFeatured>()
        var featured = LearnFeatured()
        featured.img = R.drawable.deep_thinking_new
        featured.name = "Deep Thinking"
        featured.price = "$19.99"

        var featured5 = LearnFeatured()
        featured5.img = R.drawable.cloud10
        featured5.name = "Web, Cloud and Mobile Solutions with F#"
        featured5.price = "$16.99"
        featured5.strikePrice = "$20.99"

        var featured2 = LearnFeatured()
        featured2.img = R.drawable.data_science
        featured2.name = "Data Science"
        featured2.price = "$16.99"
        featured2.strikePrice = "$20.99"

        var featured3 = LearnFeatured()
        featured3.img = R.drawable.computer_programing_
        featured3.name = "Computer Programming"
        featured3.price = "$10.99"

        var featured4 = LearnFeatured()
        featured4.img = R.drawable.neural_network_
        featured4.name = "Neural Network"
        featured4.price = "$16.99"



        list.add(featured)
        list.add(featured2)
        list.add(featured3)
        list.add(featured4)
        list.add(featured5)

        return list
    }
}