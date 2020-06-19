package com.example.android.androidarchkotlin.model

import com.example.android.androidarchkotlin.db.model.MainEntity
import com.google.gson.annotations.SerializedName

data class Main(
    val data: Data
) {
    fun toEntity() = MainEntity(
        nazarLocation =
        "Today madam nazar is at ${data.location.region.name}, " +
            "precisely in ${data.location.region.precise} " +
            "and close to: ${data.location.nearBy.joinToString(", ") { it }}"
    )
}

data class Data(
    val id: Long,
    val name: String,
    val location: Location
)

data class Location(
    val region: Region,
    val cardinals: Cardinals,
    @SerializedName("near_by")
    val nearBy: List<String>,
    val image: String
)

data class Cardinals(
    val full: String,
    val simplified: String
)

data class Region(
    val name: String,
    val precise: String
)