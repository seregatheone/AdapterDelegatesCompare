package com.patproject.test.recviewadapterdelagate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.patproject.test.api.models.PhotoClass
import com.patproject.test.api.models.PhotoRefClass
import com.patproject.test.api.models.PostClass
import com.patproject.test.recviewadapterdelagate.data.repository.DataRepository
import com.patproject.test.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class TestFragmentViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getPosts() = flow{
        try {
            emit(Resource.success(data = dataRepository.getPosts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        Resource.loading(data = null)
    )
    fun getPhotos() = flow{
        try {
            emit(Resource.success(data = dataRepository.getPhotos()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        Resource.loading(data = null)
    )
    fun getRefPhotos() = flow{
        try {
            emit(Resource.success(data = dataRepository.getPhotosRefs()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        Resource.loading(data = null)
    )


    companion object{
        @Suppress("UNCHECKED_CAST")
        class TestFragmentViewModelFactory @Inject constructor(
            private val dataRepository: DataRepository
        ) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TestFragmentViewModel(dataRepository) as T
            }
        }
    }
}