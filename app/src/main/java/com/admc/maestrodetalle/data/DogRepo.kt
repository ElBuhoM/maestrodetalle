package com.admc.maestrodetalle.data

import com.admc.maestrodetalle.data.model.DogModel
import com.admc.maestrodetalle.data.network.DogService
import javax.inject.Inject

class DogRepo @Inject constructor(private val api:DogService) {
    suspend fun getDogsByBreads(url:String):List<String> {
        return api.getDogImages(url)
    }
}