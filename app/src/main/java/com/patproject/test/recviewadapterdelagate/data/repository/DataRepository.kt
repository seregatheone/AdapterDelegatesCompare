package com.patproject.test.recviewadapterdelagate.data.repository

import com.patproject.test.api.DataService
import javax.inject.Inject

class DataRepository @Inject constructor(private val dataService :DataService) {
    suspend fun getPosts() = dataService.getPosts()
    suspend fun getPhotosRefs() = dataService.getPhotosRefs()
    suspend fun getPhotos() = dataService.getPhotos()
}