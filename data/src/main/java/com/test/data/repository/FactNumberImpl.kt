package com.test.data.repository

import com.test.data.api.data_source.NumberNetSource
import com.test.data.extensions.map
import com.test.data.extensions.mapFactEntity
import com.test.data.room.data_source.FactDBSource
import com.test.domain.model.FactModel
import com.test.domain.repository.FactNumberRepository

class FactNumberImpl (private val numberNetSource: NumberNetSource,  private val factDBSource: FactDBSource) : FactNumberRepository{
    override suspend fun getFactNumber(
        number: String,
        onSuccess: (String?) -> Unit,
        onError: (Exception) -> Unit
    ) {
        numberNetSource.getFactNumber(number,onSuccess, onError)
    }

    override suspend fun insertFact(factModel: FactModel) {
        return factDBSource.insertFact(factModel.map())
    }

    override suspend fun getFacts(): List<FactModel>? {
        return factDBSource.getFacts()?.mapFactEntity()
    }
}