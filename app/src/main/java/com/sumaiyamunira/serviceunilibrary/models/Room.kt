package com.sumaiyamunira.serviceunilibrary.models

import java.io.Serializable

data class Room(
    val roomId: String,
    val roomImage: Int,
    val name: String,
    val locationAddress: String,
    val capacity: Int,
    val hasMultimedia: Boolean,
    val hasWhiteboard: Boolean,
    var startTime: String,
    var endTime: String,
    var meetingDate: Long,
    var displayDate : String
) : Serializable
