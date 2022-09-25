package com.example.myweather.domain.usecase

import com.example.myweather.domain.repository.LocalRepository
import javax.inject.Inject

class GetLastCityUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke() = repository.getLastCity()
}
