package com.sumaiyamunira.serviceunilibrary.utils


data class ProcessMiningRecord(
    val processId : String,
    val timestamp: String,
    val timestamp_end: String,
    val activity: String,
    val resource: String,
    val role : String,
    // Other properties...
)