package com.sumaiyamunira.serviceunilibrary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.adapter.AuthorListAdapter
import com.sumaiyamunira.serviceunilibrary.adapter.LearnerRecyclerViewAdapter
import com.sumaiyamunira.serviceunilibrary.databinding.LearnActivitySearchBinding
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemFeaturedBinding
import com.sumaiyamunira.serviceunilibrary.databinding.LearnItemSearchBinding
import com.sumaiyamunira.serviceunilibrary.models.LearnFeatured
import com.sumaiyamunira.serviceunilibrary.models.LearnPeople
import com.sumaiyamunira.serviceunilibrary.utils.*

class LearnSearchFragment : Fragment() {
    private lateinit var binding: LearnActivitySearchBinding
    private var selectedCategoryPosition: Int = -1


    val authorListAdapter = AuthorListAdapter(
        layoutBindingInflater = { layoutInflater, parent, attachToParent ->
            LearnItemSearchBinding.inflate(layoutInflater, parent, attachToParent)
        },
        onItemClick = { featuredItem ->
        }
    )


    val mFeaturedAdapter = LearnerRecyclerViewAdapter(
        layoutBindingInflater = { layoutInflater, parent, attachToParent ->
            LearnItemFeaturedBinding.inflate(layoutInflater, parent, attachToParent)
        },
        onItemClick = { featuredItem ->
        }
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LearnActivitySearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvRecommended.setHorizontalLayout()
        binding.rvRecommended.adapter = mFeaturedAdapter

        binding.rvPeoples.setHorizontalLayout()
        binding.rvPeoples.rvItemAnimation()
        binding.rvPeoples.adapter = authorListAdapter

        mFeaturedAdapter.clearItems()
        authorListAdapter.clearItems()
        mFeaturedAdapter.addItems(getFeaturedItems())
        authorListAdapter.addItems(getPeoples())
    }


    private fun getFeaturedItems(): List<LearnFeatured> {
        var list = ArrayList<LearnFeatured>()
        var featured = LearnFeatured()
        featured.img = R.drawable.learn_walk_1
        featured.name = "Business Management"
        featured.price = "$19.99"

        var featured2 = LearnFeatured()
        featured2.img = R.drawable.learn_walk_2
        featured2.name = "Learn How To Play Guitar"
        featured2.price = "$16.99"
        featured2.strikePrice = "$20.99"

        var featured3 = LearnFeatured()
        featured3.img = R.drawable.learn_walk_3
        featured3.name = "Medicine & Biology Basics"
        featured3.price = "$10.99"


        list.add(featured)
        list.add(featured2)
        list.add(featured3)

        return list
    }

    private fun getPeoples(): List<LearnPeople> {
        var list = ArrayList<LearnPeople>()

        var people = LearnPeople()
        people.img = R.drawable.learn_profile_7
        people.name = "James"
        people.subject = "Big Data"
        people.isOnline = true

        list.add(people)

        var people2 = LearnPeople()
        people2.img = R.drawable.learn_profile_8
        people2.name = "Anna"
        people2.subject = "HCI"
        list.add(people2)

        var people3 = LearnPeople()
        people3.img = R.drawable.learn_profile_9
        people3.name = "Louisa"
        people3.subject = "JAVA"
        people3.isOnline = true

        list.add(people3)

        var people4 = LearnPeople()
        people4.img = R.drawable.learn_profile_6
        people4.name = "Walter"
        people4.subject = "C++"
        list.add(people4)

        var people5 = LearnPeople()
        people5.img = R.drawable.learn_profile_3
        people5.name = "Emma"
        people5.subject = "AI"
        people5.isOnline = true

        list.add(people5)

        var people6 = LearnPeople()
        people6.img = R.drawable.learn_profile_5
        people6.name = "Julie Gonas"
        people6.subject = "Business"
        people6.isOnline = true

        list.add(people6)

        return list
    }
}
