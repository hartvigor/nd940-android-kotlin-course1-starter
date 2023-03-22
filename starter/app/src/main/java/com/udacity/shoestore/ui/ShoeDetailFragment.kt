package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeDetailFragment: Fragment() {
    lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.buttonSave.setOnClickListener {
            saveShoe()
            findNavController().navigate(R.id.action_shoeDetail_to_shoeListFragment)
        }
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetail_to_shoeListFragment)
        }
        return binding.root
    }

    fun saveShoe() {
        val shoeName = binding.editShoeDetail.text.toString()
        val shoeSize = binding.editShowSize.text.toString().toDouble()
        val shoeCompany = binding.editCompany.text.toString()
        val shoeDesc = binding.editShoeDescription.text.toString()

        val newShoe = Shoe(shoeName, shoeSize, shoeCompany, shoeDesc)
        viewModel.addShoe(newShoe)
    }

}