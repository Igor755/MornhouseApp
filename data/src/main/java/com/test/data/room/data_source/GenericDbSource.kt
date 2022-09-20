package com.test.data.room.data_source

import com.test.data.entity.room.GenericEntity
import com.test.data.room.daos.GenericDao

class GenericDbSource(private val genericDao: GenericDao) {
    suspend fun getGeneric(id: Long) = genericDao.getGeneric(id)
    suspend fun insertGenerics(generics: List<GenericEntity>) = genericDao.insertGenerics(generics)
}