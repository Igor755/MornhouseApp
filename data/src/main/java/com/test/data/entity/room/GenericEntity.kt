package com.test.data.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GenericEntity")
data class GenericEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long
)