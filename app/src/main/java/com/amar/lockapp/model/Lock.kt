package com.amar.lockapp.model

data class Lock(
    val buildingId: String,
    val description: Any,
    val floor: String,
    val id: String,
    val name: String,
    val roomNumber: String,
    val serialNumber: String,
    val type: String
)