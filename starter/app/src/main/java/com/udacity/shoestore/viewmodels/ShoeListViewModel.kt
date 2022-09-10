package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel(){
    var shoeList = mutableListOf<Shoe>()

    val _shoeListLiveData = MutableLiveData<MutableList<Shoe>>()
    val shoeListLiveData: LiveData<MutableList<Shoe>>
        get() = _shoeListLiveData

    init {
        addShoe(Shoe("Kicks", 42.0, "Adidas", "Very nice!"))
    }


    fun addShoe(newShoe: Shoe){
        _shoeListLiveData.value?.add(newShoe)
    }
}

    /**data class Shoes(
        val shoeName: String,
        val company: String,
        val shoeSize: Int,
        val description: String
    )

    lateinit var shoes: MutableList<Shoe> // = mutableListOf(Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes!"))*/
/**
    // List of shoes
    private val shoeList = mutableListOf<Shoe>()//MutableLiveData<List<Shoe>>()////MutableLiveData<MutableList<Shoe>>()

    val _shoeListLiveData = MutableLiveData<List<Shoe>>()

    val shoeListLiveData: LiveData<List<Shoe>>
        get() = _shoeListLiveData


    init {
        val shoe1 = Shoe("Kicks", 42.0, "Adidas", "Very nice!")
        val shoe2 = Shoe(name= "Air", size = 42.0, company = "Nike", description = "Nice shoes!")

        addShoe(shoe1)
        addShoe(shoe2)

        /**_shoeList.value = mutableListOf<Shoe>(
            Shoe("Kicks", 42.0, "Adidas", "Very nice!"),
            Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes!")
        )
    }

    // When user adds a new shoe from ShoeDetail
    fun addShoe(newShoe: Shoe) {
        shoeList.add(newShoe)
        _shoeListLiveData.value = shoeList
    }
 */