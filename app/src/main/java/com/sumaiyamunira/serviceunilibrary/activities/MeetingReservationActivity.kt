package com.sumaiyamunira.serviceunilibrary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.base.LearnerBaseActivity
import com.sumaiyamunira.serviceunilibrary.databinding.ActivityMeetingReservationBinding
import com.sumaiyamunira.serviceunilibrary.models.Room
import com.sumaiyamunira.serviceunilibrary.models.User
import com.sumaiyamunira.serviceunilibrary.utils.HelperClass
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources
import com.sumaiyamunira.serviceunilibrary.utils.onClick

class MeetingReservationActivity : LearnerBaseActivity() {
    lateinit var roomDto: Room
    private lateinit var binding: ActivityMeetingReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetingReservationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setStatusBar(this, R.color.learn_gradient_grey_1)

        getIntentData()
        loadData()

    }


    private fun getIntentData() {
        val intent = intent
        if (intent != null) {
            val roomObject = intent.getSerializableExtra("roomObject") as? Room
            if (roomObject != null) {
                roomDto = roomObject
            } else {
            }
        }
    }

    private fun loadData() {
        binding.ivMeetingImage.loadImageFromResources(ServiceUniLibraryApp.getAppInstance(), roomDto.roomImage)

        binding.tvRoomName.text = "Room ID: " + roomDto.roomId
        binding.tvLocation.text = "Location: " + roomDto.locationAddress


        val detailTxt = "Date: " + HelperClass.formatDateFromTimestamp(roomDto.meetingDate) + "\n" +
                "Time: " + roomDto.startTime + " to " + roomDto.endTime + "\n\n" +
                "Capacity: " + roomDto.capacity + "\n" +
                "Board: " + (if (roomDto.hasWhiteboard) "Yes" else "No") + "\n" +
                "Multimedia: " + (if (roomDto.hasMultimedia) "Yes" else "No")

        binding.tvDetail.text = detailTxt

        binding.btnConfirm.onClick {
            val sharedPrefUtils = ServiceUniLibraryApp.getSharedPrefUtils()
            if (sharedPrefUtils != null) {
                val userDataJson = sharedPrefUtils.getStringValue("user_data")
                if (userDataJson != null) {
                    val retrievedUserData = User.fromJson(userDataJson)
                    var retrievedRoomHashMap = sharedPrefUtils?.getRoomHashMapFromSharedPreferences(
                        ServiceUniLibraryApp.getAppInstance(),
                        "roomHashMapKey"
                    ) ?: HashMap()

                    var totalBookedList = retrievedRoomHashMap.get(retrievedUserData.username)
                    if (totalBookedList == null) {
                        totalBookedList = ArrayList()
                    }
                    totalBookedList.add(roomDto)
                    retrievedRoomHashMap[retrievedUserData.username] = totalBookedList

                    sharedPrefUtils.saveRoomHashMapToSharedPreferences(
                        ServiceUniLibraryApp.getAppInstance(),
                        "roomHashMapKey",
                        retrievedRoomHashMap
                    )


                    HelperClass.storeData(
                        "Send Booking Confirmation Request",
                        retrievedUserData.fullname,
                        "Room Confirmer"
                    )


                    HelperClass.storeData(
                        "Receive Booked Room Detail",
                        retrievedUserData.fullname,
                        "Room Booker"
                    )

                }

                var retrievedBooleanHashMap =
                    sharedPrefUtils.getBooleanHashMapFromSharedPreferences(
                        ServiceUniLibraryApp.getAppInstance(),
                        "totalBookingHashMap"
                    )

                if (retrievedBooleanHashMap == null) {
                    retrievedBooleanHashMap = HashMap<String, Boolean>()
                }


                val keyIs = HelperClass.generateMapKey(
                    roomDto.displayDate,
                    roomDto.roomId,
                    roomDto.startTime
                )

                retrievedBooleanHashMap.put(
                    keyIs, true
                )

                Log.e("ServiceUniApp", "key while saving " + keyIs)

                sharedPrefUtils.saveBooleanHashMapToSharedPreferences(
                    ServiceUniLibraryApp.getAppInstance(),
                    "totalBookingHashMap",
                    retrievedBooleanHashMap
                )
                finish()
            }
        }

        binding.tvCancel.onClick {
            val sharedPrefUtils = ServiceUniLibraryApp.getSharedPrefUtils()
            if (sharedPrefUtils != null) {
                val userDataJson = sharedPrefUtils.getStringValue("user_data")
                if (userDataJson != null) {
                    val retrievedUserData = User.fromJson(userDataJson)
                    HelperClass.storeData(
                        "Send Reservation Cancelation Request",
                        retrievedUserData.fullname,
                        "Reservation Canceler"
                    )
                }
            }


            finish()
        }
        binding.ivBack.onClick {
            finish()
        }

    }
}