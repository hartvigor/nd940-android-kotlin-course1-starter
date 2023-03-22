package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel(){
    // List of shoes
    private val _shoeList = MutableLiveData<List<Shoe>>(emptyList())//mutableListOf<Shoe>()
    val shoeList: LiveData<List<Shoe>> = _shoeList

    fun addShoe(newShoe: Shoe) {
        _shoeList.value = _shoeList.value?.plus(newShoe)
    }

    fun editShoe(index: Int, updatedShoe: Shoe) {
        val updatedList = _shoeList.value?.toMutableList() ?: mutableListOf()
        // indices returns range of Ints within the valid lists index
        if (index in updatedList.indices) {
            updatedList[index] = updatedShoe
            // Notify observer of updated Shoe values
            _shoeList.value = updatedList
        }
    }
}