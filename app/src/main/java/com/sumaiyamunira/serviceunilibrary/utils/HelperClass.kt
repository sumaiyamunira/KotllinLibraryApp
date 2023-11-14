package com.sumaiyamunira.serviceunilibrary.utils

import android.os.Environment
import android.util.Log
import com.opencsv.CSVWriter
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.models.Room
import com.sumaiyamunira.serviceunilibrary.models.User
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

public class HelperClass {

    companion object {
        val processMiningData = mutableListOf<ProcessMiningRecord>()
        var processID = 0;
        val userList = listOf(
            User(1, "sumaiya", "Sumaiya Munira"),
            User(2, "student2", "Student2 Full Name"),
            User(3, "student3", "Student3 Full Name"),
        )


        val totalMeetingRoomList = listOf(
            Room(
                "MR101",
                R.drawable.meeting_room1,
                "Meeting Room 1",
                "First Floor",
                5,
                true,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR102",
                R.drawable.meeting_room2,
                "Meeting Room 2",
                "First Floor",
                4,
                true,
                false,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR103",
                R.drawable.meeting_room3,
                "Meeting Room 3",
                "First Floor",
                3,
                true,
                false,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR104",
                R.drawable.meeting_room4,
                "Meeting Room 4",
                "First Floor",
                8,
                true,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR105",
                R.drawable.meeting_room5,
                "Meeting Room 5",
                "First Floor",
                7,
                true,
                true,
                "",
                "",
                0,
                ""
            ),

            Room(
                "MR201",
                R.drawable.meeting_room6,
                "Meeting Room 6",
                "Second Floor",
                10,
                true,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR202",
                R.drawable.meeting_room7,
                "Meeting Room 7",
                "Second Floor",
                8,
                true,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR203",
                R.drawable.meeting_room8,
                "Meeting Room 8",
                "Second Floor",
                5,
                false,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR204",
                R.drawable.meeting_room9,
                "Meeting Room 9",
                "Second Floor",
                4,
                false,
                false,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR205",
                R.drawable.meeting_room10,
                "Meeting Room 10",
                "Second Floor",
                10,
                true,
                false,
                "",
                "",
                0,
                ""
            ),

            Room(
                "MR206",
                R.drawable.meeting_room11,
                "Meeting Room 11",
                "Second Floor",
                5,
                true,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR207",
                R.drawable.meeting_room12,
                "Meeting Room 12",
                "Second Floor",
                5,
                false,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR301",
                R.drawable.meeting_room13,
                "Meeting Room 13",
                "Third Floor",
                5,
                true,
                false,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR302",
                R.drawable.meeting_room14,
                "Meeting Room 14",
                "Third Floor",
                5,
                true,
                true,
                "",
                "",
                0,
                ""
            ),
            Room(
                "MR303",
                R.drawable.meeting_room15,
                "Meeting Room 15",
                "Third Floor",
                5,
                true,
                true,
                "",
                "",
                0,
                ""
            ),

            )


        public fun formatDateFromTimestamp(timestamp: Long): String {
            val date = Date(timestamp)
            val dateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
            val dayOfMonth = SimpleDateFormat("d", Locale.getDefault()).format(date).toInt()
            val suffix = getDayOfMonthSuffix(dayOfMonth)
            val formattedDate = dateFormat.format(date)
            return formattedDate
        }

        fun getDayOfMonthSuffix(n: Int): String {
            if (n in 11..13) {
                return "th"
            }
            when (n % 10) {
                1 -> return "st"
                2 -> return "nd"
                3 -> return "rd"
                else -> return "th"
            }
        }

        fun convertTimestampTo24HourClock(timestamp: Long): String {
            val date = Date(timestamp)
            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            return format.format(date)
        }


        fun isUsernameExists(username: String): Boolean {
            for (user in userList) {
                if (user.username == username) {
                    return true
                }
            }
            return false
        }

        fun getUserDTO(username: String): User? {
            for (user in userList) {
                if (user.username == username) {
                    return user
                }
            }
            return null
        }

        fun generateMapKey(date: String, roomId: String, startTime: String): String {
            // Concatenate the values into a single string with a separator
            val separator = "_"
            return "$date$separator$roomId$separator$startTime"
        }


        fun generateAvailableRoomToListIfNotBooked(
            bookingMap: MutableMap<String, Boolean>, date: String, time: String
        ): MutableList<Room> {
            val myAvailableRoom = mutableListOf<Room>()
            for (room in totalMeetingRoomList) {
                val key = generateMapKey(date, room.roomId, time)
                Log.e("ServiceUniApp", "generated key while retrieving " + key)
                if (!bookingMap.containsKey(key)) {
                    Log.e("ServiceUniApp", "yes not contain " + key)
                    myAvailableRoom.add(room)
                } else {
                    Log.e("ServiceUniApp", "yes contain " + key)
                }
            }
            return myAvailableRoom
        }


         fun formatMillisToDateTime(timestamp: Long): String {
             val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
             val date = Date(timestamp)
             val formattedTime = sdf.format(date)

            return formattedTime
        }


        fun storeData(activityName: String, userName : String, role:String ) {
            processID = 1+processID
            val timestamp = System.currentTimeMillis()
            val timestamp_end = timestamp
            processMiningData.add(ProcessMiningRecord(""+processID, formatMillisToDateTime(timestamp),
                formatMillisToDateTime(timestamp_end), activityName, userName, role))
        }


        fun exportProcessMiningDataToCsv(data: List<ProcessMiningRecord>, filePath: String) {
            val writer = CSVWriter(FileWriter(filePath))
            val header = arrayOf("Case ID", "Timestamp (start)", "Timestamp (complete)", "Activity", "Resource", "Role") // Define CSV header

            writer.writeNext(header)
            Log.e("Service uni stored log size", ""+data.size)
            for (record in data) {
                val row = arrayOf(record.processId, record.timestamp, record.timestamp_end, record.activity, record.resource, record.role)
                writer.writeNext(row)
            }

            writer.close()

        }

        public fun generateCSV() {
            val dataToExport = processMiningData
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val dir = File(path.absolutePath)
            if(!dir.exists())
                dir.mkdirs()
            val file = File("$path/process_mining_data.csv")
            if (!file.exists()) {
                file.createNewFile();
            }
            exportProcessMiningDataToCsv(dataToExport, "$path/process_mining_data.csv")
        }

    }



}


