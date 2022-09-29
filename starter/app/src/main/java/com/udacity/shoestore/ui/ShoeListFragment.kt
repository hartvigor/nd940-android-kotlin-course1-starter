package com.udacity.shoestore.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ListItemShoeBinding
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



        binding.actionButtonShoelist.setOnClickListener { view: View ->
            addShoe()
            //Navigation.findNavController(view).navigate(R.id.action_shoeListFragment_to_shoeDetail)
        }

        viewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->

            /**
             * removeALlViews() must be called before adding a new view or else it is
             * just added in addition to the existing and not replaced.
             */
            binding.shoeListLayout.removeAllViews()

            /**
             * Iterates through the shoeLists and adds each element (Shoe) to the view
             * when the livedata is updated.
             */
            shoeList.forEach {
                val shoesBinding = DataBindingUtil.inflate<ListItemShoeBinding>(
                    inflater, R.layout.list_item_shoe, binding.shoeListLayout, false
                )
                //shoesBinding.shoe = it
                shoesBinding.shoeName.text = it.name
                shoesBinding.shoeSize.text = it.size.toString()
                shoesBinding.shoeCompany.text = it.company
                shoesBinding.shoeDescription.text = it.description

                binding.shoeListLayout.addView(shoesBinding.root)
            }
        }

        //addShoe()
        loadShoe()
        return binding.root
    }

    private fun loadShoe() {
        if (!viewModel.firstShoe) {
            addShoe()
            viewModel.firstShoe = true
        }
    }

    fun addShoe(){
        val shoe = Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes! ")
        //viewModel.shoes.add(shoe)
        viewModel.addShoe(shoe)
    }
}