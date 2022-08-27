package com.patproject.test.api.models


import com.google.gson.annotations.SerializedName
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class PhotoClass(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
): ViewTyped