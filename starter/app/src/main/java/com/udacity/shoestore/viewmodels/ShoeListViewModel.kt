package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel(){
    // Boolean to keep track if test shoe is already added the first time
    var firstShoe = false

    // List of shoes
    private val _shoeList = MutableLiveData<List<Shoe>>(emptyList())//mutableListOf<Shoe>()
    val shoeList: LiveData<List<Shoe>> = _shoeList

    fun addShoe(newShoe: Shoe) {
        _shoeList.value = _shoeList.value?.plus(newShoe)
    }
}