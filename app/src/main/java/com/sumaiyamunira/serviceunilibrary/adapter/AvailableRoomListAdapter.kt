package com.sumaiyamunira.serviceunilibrary.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.activities.MeetingReservationActivity
import com.sumaiyamunira.serviceunilibrary.models.Room
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources
import com.sumaiyamunira.serviceunilibrary.utils.onClick

class AvailableRoomListAdapter(private var roomList: List<Room>) :
    RecyclerView.Adapter<AvailableRoomListAdapter.RoomViewHolder>() {
    private var clickListener: RoomClickListener? = null

    fun setRoomClickListener(listener: RoomClickListener) {
        clickListener = listener
    }

    fun updateList(newRoomList: List<Room>) {
        roomList = newRoomList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_available_room_item, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentRoom = roomList[position]
        holder.meeting_room_name.text = "Room ID: " + currentRoom.roomId
        holder.meeting_date_time.text = "Location: " + currentRoom.locationAddress

        holder.tvwhiteboard.text = if (currentRoom.hasWhiteboard) "Yes" else "No"
        holder.tvmultimedia.text = if (currentRoom.hasMultimedia) "Yes" else "No"
        holder.tvcapacity.text = "" + currentRoom.capacity

        holder.meeting_room_img.loadImageFromResources(
            ServiceUniLibraryApp.getAppInstance(),
            currentRoom.roomImage
        )

        holder.linear_main_holder.setOnClickListener {
            clickListener?.onRoomClick(currentRoom)
        }

    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val meeting_room_name: TextView = itemView.findViewById(R.id.meeting_room_name)
        val meeting_room_img: ImageView = itemView.findViewById(R.id.meeting_room_img)
        val meeting_date_time: TextView = itemView.findViewById(R.id.meeting_date_time)
        val tvwhiteboard: TextView = itemView.findViewById(R.id.tvwhiteboard)
        val tvmultimedia: TextView = itemView.findViewById(R.id.tvmultimedia)
        val tvcapacity: TextView = itemView.findViewById(R.id.tvcapacity)
        val linear_main_holder: LinearLayout = itemView.findViewById(R.id.linear_main_holder)

    }


    interface RoomClickListener {
        fun onRoomClick(room: Room)
    }


}




