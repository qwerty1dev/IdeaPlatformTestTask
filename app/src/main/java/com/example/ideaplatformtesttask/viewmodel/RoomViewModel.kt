package com.example.ideaplatformtesttask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideaplatformtesttask.data.model.ItemCard
import com.example.ideaplatformtesttask.data.room.RoomRepository
import com.example.ideaplatformtesttask.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val itemCardList = MutableStateFlow<List<ItemCard>>(emptyList())
    val itemCardListResult: StateFlow<List<ItemCard>> get() = itemCardList


    fun getAllData() {
        viewModelScope.launch(ioDispatcher) {
            val result = roomRepository.getAll()
            itemCardList.value = result
        }
    }


    fun searchProducts(query: String) {
        viewModelScope.launch {
            itemCardList.value = if (query.isBlank()) {
                roomRepository.getAll()
            } else {
                roomRepository.searchItemCards(query)
            }
        }
    }

    fun updateQuantity(itemCard: ItemCard, newQuantity: Int) {
        viewModelScope.launch {
            val updatedProduct = itemCard.copy(amount = newQuantity)
            roomRepository.updateProduct(updatedProduct)
            getAllData()

        }
    }



    fun deleteCard(itemCard: ItemCard) {
        viewModelScope.launch(ioDispatcher) {
            roomRepository.deleteItemCard(itemCard)
            getAllData()
        }
    }



}