package com.sumaiyamunira.serviceunilibrary.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.activities.AvailableMeetingRoomListActivity
import com.sumaiyamunira.serviceunilibrary.adapter.BookedRoomListAdapter
import com.sumaiyamunira.serviceunilibrary.databinding.FragmentMeetingRoomBinding
import com.sumaiyamunira.serviceunilibrary.models.Room
import com.sumaiyamunira.serviceunilibrary.models.User
import com.sumaiyamunira.serviceunilibrary.utils.HelperClass


class BookMeetingRoomFragment : Fragment() {
    private lateinit var binding: FragmentMeetingRoomBinding
    private lateinit var adapter: BookedRoomListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeetingRoomBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBookRoom.setOnClickListener {
            val intent = Intent(activity, AvailableMeetingRoomListActivity::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)
        }
        binding.bookedroomlist.layoutManager = LinearLayoutManager(activity)
        adapter = BookedRoomListAdapter(getRoomList())
        binding.bookedroomlist.adapter = adapter
    }

    private fun getRoomList(): List<Room> {
        val sharedPrefUtils = ServiceUniLibraryApp.getSharedPrefUtils()
        val userRoomList = ArrayList<Room>()
        if (sharedPrefUtils != null) {
            val userDataJson = sharedPrefUtils.getStringValue("user_data")
            if (userDataJson != null) {
                val retrievedUserData = User.fromJson(userDataJson)
                HelperClass.storeData("Retrieve Booked Rooms", retrievedUserData.fullname, "Room Booker")
                val retrievedRoomHashMap = sharedPrefUtils.getRoomHashMapFromSharedPreferences(
                    ServiceUniLibraryApp.getAppInstance(),
                    "roomHashMapKey"
                )
                val totalBookedList = retrievedRoomHashMap?.get(retrievedUserData.username)
                if (totalBookedList != null) {
                    userRoomList.addAll(totalBookedList)
                }
            }
        }
        return userRoomList
    }

    override fun onResume() {
        super.onResume()
        val updatedData = getRoomList()
        adapter.updateData(updatedData)
    }


}