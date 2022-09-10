package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeListFragment: Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding.lifecycleOwner = this

        //val myLayout: LinearLayout = view?.findViewById(R.id.shoeDetailLayout) as LinearLayout
        (view as? ViewGroup)?. let {
            View.inflate(context, R.layout.fragment_shoe_detail, container)
        }

        binding.actionButtonShoelist.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoeListFragment_to_shoeDetail)
        }



        // Aquire the viewmodel
        //viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

        //TEST ADD SHOE
        val shoe = Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes!")
        viewModel.addShoe(shoe)

        return binding.root
    }
}