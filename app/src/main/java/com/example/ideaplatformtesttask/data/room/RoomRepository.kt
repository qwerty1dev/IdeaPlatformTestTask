package com.example.ideaplatformtesttask.data.room

import com.example.ideaplatformtesttask.data.model.ItemCard
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val itemCardDao: ItemCardDao
) {
    suspend fun getAll(): List<ItemCard> {
        return itemCardDao.getAll()
    }

    suspend fun searchItemCards(query: String): List<ItemCard> {
        return itemCardDao.searchItemCards("%$query%")
    }

    suspend fun updateProduct(card: ItemCard) {
        itemCardDao.updateItemCard(card)
    }

    suspend fun deleteItemCard(card: ItemCard) {
        itemCardDao.deleteItemCard(card)
    }

}