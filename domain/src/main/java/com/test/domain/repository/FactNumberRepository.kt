package com.test.domain.repository

import com.test.domain.model.FactModel

interface FactNumberRepository {
        suspend fun getFactNumber(
            number : String,
            onSuccess: (String?) -> Unit,
            onError: (Exception) -> Unit
        )

    suspend fun insertFact(factModel: FactModel)
    suspend fun getFacts() : List<FactModel>?
}