package com.test.data.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val text: String? = null,
)