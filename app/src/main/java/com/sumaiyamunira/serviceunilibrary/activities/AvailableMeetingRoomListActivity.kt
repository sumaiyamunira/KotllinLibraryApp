package com.sumaiyamunira.serviceunilibrary.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.adapter.AvailableRoomListAdapter
import com.sumaiyamunira.serviceunilibrary.base.LearnerBaseActivity
import com.sumaiyamunira.serviceunilibrary.models.Room
import com.sumaiyamunira.serviceunilibrary.models.User
import com.sumaiyamunira.serviceunilibrary.utils.HelperClass
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.properties.Delegates

class AvailableMeetingRoomListActivity : LearnerBaseActivity(), AvailableRoomListAdapter.RoomClickListener {

    private lateinit var date_picker_btn: MaterialButton
    private lateinit var tvClear: ImageView
    private lateinit var availableList: RecyclerView
    private lateinit var start_time_btn: MaterialButton
    private lateinit var end_time_btn: MaterialButton
    private lateinit var adapter: AvailableRoomListAdapter
    private var selectedDateTimestamp by Delegates.notNull<Long>()


    val hashMapStartTime = hashMapOf<String, String>()
    val hashMapEndTime = hashMapOf<String, String>()
    val startTime = arrayOf("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00")
    val endTime = arrayOf("10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_meeting_room_list)
        setStatusBar(this, R.color.learn_gradient_grey_1)
        generateHashMapValues()

        availableList = findViewById(R.id.availableList)
        start_time_btn = findViewById(R.id.start_time_btn)
        end_time_btn = findViewById(R.id.end_time_btn)


        date_picker_btn = findViewById(R.id.date_picker_btn)
        date_picker_btn.setOnClickListener {
            showDatePickerDialog()
        }
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        selectedDateTimestamp = calendar.timeInMillis
        val formattedDate = formatDate(year, month, dayOfMonth)
        date_picker_btn.text = formattedDate

        tvClear = findViewById(R.id.tvClear)
        tvClear.setOnClickListener {
            finish()
        }

        start_time_btn.text = "09:00"
        end_time_btn.text = "10:00"
        start_time_btn.setOnClickListener {
            showItemPickerDialogFragment(supportFragmentManager, startTime)
        }


        end_time_btn.setOnClickListener {
            showItemPickerDialogFragmentEndTime(supportFragmentManager, endTime)
        }

        availableList.layoutManager = LinearLayoutManager(this)
        adapter = AvailableRoomListAdapter(listOf<Room>())
        adapter.setRoomClickListener(this)
        availableList.adapter = adapter

        generateListData()

    }

    private fun generateHashMapValues() {
        hashMapStartTime["09:00"] = "10:00"
        hashMapStartTime["10:00"] = "11:00"
        hashMapStartTime["11:00"] = "12:00"
        hashMapStartTime["12:00"] = "13:00"
        hashMapStartTime["13:00"] = "14:00"
        hashMapStartTime["14:00"] = "15:00"
        hashMapStartTime["15:00"] = "16:00"
        hashMapStartTime["16:00"] = "17:00"
        hashMapStartTime["17:00"] = "18:00"

        hashMapEndTime["10:00"] = "9:00"
        hashMapEndTime["11:00"] = "10:00"
        hashMapEndTime["12:00"] = "11:00"
        hashMapEndTime["13:00"] = "12:00"
        hashMapEndTime["14:00"] = "13:00"
        hashMapEndTime["15:00"] = "14:00"
        hashMapEndTime["16:00"] = "15:00"
        hashMapEndTime["17:00"] = "16:00"
        hashMapEndTime["18:00"] = "17:00"


    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val formattedDate = formatDate(year, month, dayOfMonth)
                date_picker_btn.text = formattedDate
                generateListData()

                // Update the calendar with the selected date
                calendar.set(year, month, dayOfMonth)
                selectedDateTimestamp = calendar.timeInMillis

                generateListData()

            },
            year, month, day
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("EEE, d MMMM yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun showItemPickerDialogFragment(fragmentManager: FragmentManager, items: Array<String>) {
        val dialogFragment = ItemPickerDialogFragment()

        dialogFragment.arguments = Bundle().apply {
            putStringArray("items", items)
            putString("title", "Choose Start Time")
        }
        dialogFragment.setItemSelectedListener { selectedItem ->
            start_time_btn.text = selectedItem as CharSequence?
            end_time_btn.text = hashMapStartTime.get(selectedItem as CharSequence)
            generateListData()
        }
        // Show the dialog fragment
        dialogFragment.show(fragmentManager, "item_picker")
    }


    fun showItemPickerDialogFragmentEndTime(
        fragmentManager: FragmentManager,
        items: Array<String>
    ) {
        val dialogFragment = ItemPickerDialogFragment()

        dialogFragment.arguments = Bundle().apply {
            putStringArray("items", items)
            putString("title", "Choose End Time")
        }
        dialogFragment.setItemSelectedListener { selectedItem ->
            end_time_btn.text = selectedItem as CharSequence?
            start_time_btn.text = hashMapEndTime.get(selectedItem as CharSequence)
            generateListData()
        }
        dialogFragment.show(fragmentManager, "item_picker")
    }

    class ItemPickerDialogFragment : DialogFragment() {
        private var itemSelectedListener: ((Any) -> Unit)? = null

        // Setter method to set the listener as a lambda
        fun setItemSelectedListener(listener: (Any) -> Unit) {
            itemSelectedListener = listener
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val items = arguments?.getStringArray("items") ?: emptyArray()
            val title = arguments?.getString("title") ?: ""
            return AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setItems(items) { _, which ->
                    val selectedItem = items[which]
                    itemSelectedListener?.let { listener ->
                        listener(selectedItem)
                    }
                }
                .create()
        }

        interface ItemSelectedListener {
            fun onItemSelected(selectedItem: String)
        }
    }

    fun generateListData() {
        val sharedPrefUtils = ServiceUniLibraryApp.getSharedPrefUtils()
        if (sharedPrefUtils != null) {
            val userDataJson = sharedPrefUtils.getStringValue("user_data")
            if (userDataJson != null) {
                val retrievedUserData = User.fromJson(userDataJson)
                HelperClass.storeData(
                    "Get Avilable Room List",
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

            adapter.updateList(HelperClass.generateAvailableRoomToListIfNotBooked(
                    retrievedBooleanHashMap,
                    date_picker_btn.text.toString(),
                    start_time_btn.text.toString()
                )
            )
        }
    }

    override fun onRoomClick(room: Room) {
        val sharedPrefUtils = ServiceUniLibraryApp.getSharedPrefUtils()
        if (sharedPrefUtils != null) {
            val userDataJson = sharedPrefUtils.getStringValue("user_data")
            if (userDataJson != null) {
                val retrievedUserData = User.fromJson(userDataJson)
                HelperClass.storeData(
                    "Send Reservation Request",
                    retrievedUserData.fullname,
                    "Requester"
                )
            }
        }


        val intent = Intent(this, MeetingReservationActivity::class.java)
        room.startTime = start_time_btn.text.toString()
        room.endTime = end_time_btn.text.toString()
        room.meetingDate = selectedDateTimestamp
        room.displayDate = date_picker_btn.text.toString()
        intent.putExtra("roomObject", room)
        startActivity(intent)
        finish()
    }

}
