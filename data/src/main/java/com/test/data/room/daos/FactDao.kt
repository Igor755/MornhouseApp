package com.test.data.room.daos

import androidx.room.*
import com.test.data.entity.room.FactEntity

@Dao
abstract class FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFact(fact: FactEntity?)

    @Query("SELECT * FROM FactEntity")
    abstract suspend fun getFacts(): MutableList<FactEntity>?

}