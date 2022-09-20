package com.test.data.room.data_source

import com.test.data.entity.room.FactEntity
import com.test.data.room.daos.FactDao

class FactDBSource(private val factDao: FactDao)  {
    suspend fun getFacts() = factDao.getFacts()
    suspend fun insertFact(fact: FactEntity) = factDao.insertFact(fact)
}