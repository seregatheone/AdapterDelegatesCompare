package com.patproject.test.api

import com.patproject.test.api.models.PhotoClass
import com.patproject.test.api.models.PhotoRefClass
import com.patproject.test.api.models.PostClass
import retrofit2.http.GET

interface DataService {
    @GET("posts")
    suspend fun getPosts():List<PostClass>
    @GET("photos")
    suspend fun getPhotos():List<PhotoClass>
    @GET("photos")
    suspend fun getPhotosRefs():List<PhotoRefClass>
}