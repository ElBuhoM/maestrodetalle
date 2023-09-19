package com.admc.maestrodetalle.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admc.maestrodetalle.data.network.DogApiClient
import com.admc.maestrodetalle.data.network.DogService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val dogApiClient: DogApiClient) :
    ViewModel() {
    private val _dogImages = MutableLiveData<List<String>>()
    val dogImages: LiveData<List<String>> get() = _dogImages

    fun fetchDogImages(url: String, showError:()->Unit) {
        viewModelScope.launch {
            try {
                val callRes = dogApiClient.getDogsByBreads(url)
                val dogs = callRes.body()
                if (callRes.isSuccessful) {
                    val images = dogs?.images ?: emptyList()
                    _dogImages.value = images

                } else {
                    showError()

                }
            } catch (e: Exception) {
            }
        }
    }
}

