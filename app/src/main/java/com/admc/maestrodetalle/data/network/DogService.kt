package com.admc.maestrodetalle.data.network

import com.admc.maestrodetalle.data.model.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogService @Inject constructor(private val api: DogApiClient) {

    suspend fun getDogImages(url: String): List<String> {
        return withContext(Dispatchers.IO) {
            val response = api.getDogsByBreads(url)
            if (response.isSuccessful) {
                val dogModel = response.body()
                if (dogModel != null && dogModel.status == "success") {
                    return@withContext dogModel.images
                } else {
                    throw Exception("Response body is null or status is not 'success'")

                }
            } else {
                throw Exception("Error fetching dog images: ${response.code()}")
            }

        }
    }
}
