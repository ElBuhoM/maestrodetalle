package com.admc.maestrodetalle.data.network

import com.admc.maestrodetalle.data.model.DogModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DogApiClient {
    @GET
    suspend fun getDogsByBreads(@Url url: String): Response<DogModel>
}
