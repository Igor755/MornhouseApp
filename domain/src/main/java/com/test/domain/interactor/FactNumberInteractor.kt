package com.test.domain.interactor

import com.test.domain.model.FactModel
import com.test.domain.repository.FactNumberRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class FactNumberInteractor : KoinComponent {

    private val factNumberRepository: FactNumberRepository by inject()

    suspend fun getFactNumber(
        number : String,
        onComplete: (String?) -> Unit,
        onError: (Exception) -> Unit
    ) = factNumberRepository.getFactNumber(number, onComplete, onError)

    suspend fun insertFact(factModel: FactModel) = factNumberRepository.insertFact(factModel)

    suspend fun getFacts() = factNumberRepository.getFacts()
}