package com.impulse.impulse_driver.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.impulse.impulse_driver.database.dao.PostDao
import com.impulse.impulse_driver.model.Medicine
import com.impulse.impulse_driver.utils.UiStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MedicineViewModel(
    private val dao: PostDao
): ViewModel() {

    private val _getAllChats = MutableStateFlow<UiStateList<Medicine>>(UiStateList.EMPTY)
    val getAllChats = _getAllChats

    fun getAllChats() = viewModelScope.launch {
        _getAllChats.value = UiStateList.LOADING
        try {
            val response = dao.getCards()
            _getAllChats.value = UiStateList.SUCCESS(response)
        }catch (e: Exception){
            _getAllChats.value = UiStateList.ERROR(e.localizedMessage)
        }
    }

    fun removeItem(card: Medicine) = viewModelScope.launch {
        dao.delete(card)
    }

    fun saveItem(card: Medicine) = viewModelScope.launch {
        dao.saveCard(card)
    }
}