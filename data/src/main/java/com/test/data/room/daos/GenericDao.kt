package com.test.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.data.entity.room.GenericEntity

@Dao
interface GenericDao {

    @Query("SELECT * FROM genericentity WHERE id = :id")
    suspend fun getGeneric(id: Long): GenericEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenerics(generic: List<GenericEntity>)
}