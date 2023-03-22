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

    private var shoeIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shoeIndex = it.getInt("shoeIndex", -1)
        }
    }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (shoeIndex != -1) {
            setEditShowFields()
        }
    }

    private fun setEditShowFields() {
        val editShoe = viewModel.shoeList.value?.get(shoeIndex)
        editShoe?.let {
            binding.editShoeDetail.setText(it.name)
            binding.editShowSize.setText(it.size.toString())
            binding.editCompany.setText(it.company)
            binding.editShoeDescription.setText(it.description)
        }
    }

    private fun saveShoe() {
        val shoe = createShoe()
        if (shoeIndex == -1) {
            viewModel.addShoe(shoe)
        } else {
            viewModel.editShoe(shoeIndex, shoe)
        }
    }

    private fun createShoe(): Shoe {
        val shoeName = binding.editShoeDetail.text.toString()
        val shoeSize = binding.editShowSize.text.toString().toDouble()
        val shoeCompany = binding.editCompany.text.toString()
        val shoeDesc = binding.editShoeDescription.text.toString()

        return Shoe(shoeName, shoeSize, shoeCompany, shoeDesc)
        //viewModel.addShoe(newShoe)
    }

}