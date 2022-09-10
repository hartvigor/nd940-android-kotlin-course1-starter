package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeAdapterBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeListViewModel
import timber.log.Timber
import java.util.*

class ShoeListFragment: Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    lateinit var binding: FragmentShoeListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        // Specifying that current activity as the licecycleowner of the binding.
        binding.lifecycleOwner = this

        binding.actionButtonShoelist.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoeListFragment_to_shoeDetail)
        }

        // Aquire the viewmodel
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)


        //TEST ADD SHOE
        val newShoe = Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes!")
        viewModel.addShoe(newShoe)

        //showShoeDetailLayout()

        return binding.root
    }

    fun showShoeDetailLayout(shoeList: List<Shoe>) {
        val shoeLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)

        for (eachShoe in shoeList) {
            var shoeInfoText = TextView(context)
            shoeInfoText.layoutParams = shoeLayout

            shoeInfoText.text = String.format(
                Locale.GERMANY.toString(),
                eachShoe.name,
                eachShoe.size,
                eachShoe.company,
                eachShoe.description
            )

            binding.shoeListLayout.addView(shoeInfoText, 0)

        }

    }
}