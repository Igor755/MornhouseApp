package com.test.data.extensions

import com.test.data.entity.network.GenericNet
import com.test.data.entity.room.FactEntity
import com.test.data.entity.room.GenericEntity
import com.test.domain.model.FactModel
import com.test.domain.model.GenericModel

fun List<GenericNet>.mapGenericNet() = this.mapTo(
    mutableListOf(), {
        it.map()
    }
)

fun GenericEntity.map() = GenericModel(id)

fun GenericNet.map() = GenericModel(id)

fun List<GenericEntity>.mapGenericEntity() = mapTo(
    mutableListOf(), {
        it.map()
    }
)

fun List<GenericModel>.mapToGenericEntity() = mapTo(mutableListOf(), {
    it.map()
})

fun GenericModel.map() = GenericEntity(id)

fun FactModel.map() = FactEntity(
    id,
    text
)

fun FactEntity.map() = FactModel(id, text)


fun List<FactEntity>.mapFactEntity() = mapTo(mutableListOf(), { it.map() })


