package com.test.mornhouse.extension

import com.test.domain.model.FactModel
import com.test.mornhouse.model.Generic
import com.test.domain.model.GenericModel
import com.test.mornhouse.model.Fact

fun List<GenericModel>.mapGeneric() = this.mapTo(mutableListOf(), {
    it.map()
})

fun GenericModel.map() = Generic(
    id = id
)


fun FactModel.map() = Fact(
    id,
    text
)

fun Fact.map() = FactModel(id, text)


fun List<FactModel>.mapFactModel() = mapTo(mutableListOf(), { it.map() })