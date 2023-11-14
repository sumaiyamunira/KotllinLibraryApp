package com.sumaiyamunira.serviceunilibrary.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp
import com.sumaiyamunira.serviceunilibrary.models.Room
import com.sumaiyamunira.serviceunilibrary.utils.HelperClass
import com.sumaiyamunira.serviceunilibrary.utils.loadImageFromResources

class BookedRoomListAdapter(private var roomList: List<Room>) :
    RecyclerView.Adapter<BookedRoomListAdapter.RoomViewHolder>() {

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val meeting_room_name: TextView = itemView.findViewById(R.id.meeting_room_name)
        val meeting_room_img: ImageView = itemView.findViewById(R.id.meeting_room_img)
        val meeting_date_time: TextView = itemView.findViewById(R.id.meeting_date_time)
        val meeting_location: TextView = itemView.findViewById(R.id.meeting_location)
        val tvwhiteboard: TextView = itemView.findViewById(R.id.tvwhiteboard)
        val tvmultimedia: TextView = itemView.findViewById(R.id.tvmultimedia)
        val tvcapacity: TextView = itemView.findViewById(R.id.tvcapacity)

    }

    fun updateData(newData: List<Room>) {
        roomList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_booked_room, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val currentRoom = roomList[position]
        holder.meeting_room_name.text = currentRoom.startTime+" - "+currentRoom.endTime
        holder.meeting_location.text = currentRoom.roomId

        holder.tvwhiteboard.text = if (currentRoom.hasWhiteboard) "Yes" else "No"
        holder.tvmultimedia.text = if (currentRoom.hasMultimedia) "Yes" else "No"
        holder.tvcapacity.text =  ""+currentRoom.capacity

        holder.meeting_room_img.loadImageFromResources(ServiceUniLibraryApp.getAppInstance(), currentRoom.roomImage)
        holder.meeting_date_time.text = HelperClass.formatDateFromTimestamp(currentRoom.meetingDate)

    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}
