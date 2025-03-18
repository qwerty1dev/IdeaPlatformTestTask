package com.example.ideaplatformtesttask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ideaplatformtesttask.utilts.AppConstant.DATABASE_NAME

@Entity(tableName = DATABASE_NAME)
data class ItemCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)