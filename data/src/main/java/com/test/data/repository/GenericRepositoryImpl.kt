package com.test.data.repository

import com.test.data.api.data_source.GenericNetSource
import com.test.data.extensions.mapGenericNet
import com.test.data.extensions.mapToGenericEntity
import com.test.data.room.data_source.GenericDbSource
import com.test.domain.model.GenericModel
import com.test.domain.repository.GenericRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GenericRepositoryImpl : GenericRepository, KoinComponent {
    private val categoriesNetSource: GenericNetSource by inject()
    private val categoriesDbSource: GenericDbSource by inject()

    override suspend fun getGenerics(
        onSuccess: (List<GenericModel>?) -> Unit,
        onError: ((Exception) -> Unit?)?
    ) {
        categoriesNetSource.getGeneric({
            onSuccess(it?.mapGenericNet()?.toList())
        }, {
            onError?.invoke(it)
        })
    }

    override suspend fun insertGenerics(
        generic: List<GenericModel>,
        onError: ((Exception) -> Unit?)?
    ) {
        try {
            categoriesDbSource.insertGenerics(generic.mapToGenericEntity())
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }

}