package com.example.ideaplatformtesttask.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.ideaplatformtesttask.data.model.ItemCard

@Dao
interface ItemCardDao {

    @Query("SELECT * FROM item")
    suspend fun getAll(): List<ItemCard>

    @Query("SELECT * FROM item WHERE name LIKE :searchQuery")
    suspend fun searchItemCards(searchQuery: String): List<ItemCard>

    @Update
    suspend fun updateItemCard(card: ItemCard)

    @Delete
    suspend fun deleteItemCard(itemCard: ItemCard)


}