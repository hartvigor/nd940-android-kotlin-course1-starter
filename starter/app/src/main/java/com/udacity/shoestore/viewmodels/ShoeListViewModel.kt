package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel(){

    var firstShoe = false

    var shoes  = mutableListOf<Shoe>()//Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes!"))//MutableList<Shoe> //

    // List of shoes
    private val _shoeList = MutableLiveData<List<Shoe>>()//mutableListOf<Shoe>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    fun addShoe(newShoe: Shoe) {
        shoes.add(newShoe)
        _shoeList.value = shoes
        //_shoeList.value?.add(newShoe)
    }
}