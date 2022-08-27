package com.patproject.test.api.models


import com.google.gson.annotations.SerializedName
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class PostClass(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
): ViewTyped