package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel(){

    /**data class Shoes(
        val shoeName: String,
        val company: String,
        val shoeSize: Int,
        val description: String
    )

    lateinit var shoes: MutableList<Shoe> // = mutableListOf(Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes!"))*/

    // List of shoes
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()//mutableListOf<Shoe>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        addShoe(Shoe("Kicks", 42.0, "Adidas", "Very nice!"))
    }

    fun addShoe(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)
    }
}