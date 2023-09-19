package com.admc.maestrodetalle.domain.usecase

import com.admc.maestrodetalle.data.DogRepo
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(private val dogRepo:DogRepo) {
    suspend operator fun invoke(url:String)= dogRepo.getDogsByBreads(url)
}