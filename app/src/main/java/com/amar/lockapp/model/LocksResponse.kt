package com.amar.lockapp.model

data class LocksResponse(
    val buildings: List<Building>,
    val groups: List<Group>,
    val locks: List<Lock>,
    val media: List<Media>
)