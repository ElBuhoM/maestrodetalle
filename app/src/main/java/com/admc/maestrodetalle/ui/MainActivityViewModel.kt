package com.admc.maestrodetalle.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.admc.maestrodetalle.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val getDogsUseCase:GetDogsUseCase) :
    ViewModel() {
    private val _dogImages = MutableLiveData<List<String>>()
    val dogImages: LiveData<List<String>> get() = _dogImages
    val isLoanding = MutableLiveData<Boolean>()

    fun fetchDogImages(url: String, showError: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoanding.postValue(true)
                val callRes = getDogsUseCase(url)
                if (callRes.isNotEmpty()) {
                    _dogImages.value = callRes

                } else {
                    showError()
                }
                isLoanding.postValue(false)
            } catch (e: Exception) {
                throw Exception("Algo salio mal en la llamada")
            }
        }
    }
}


