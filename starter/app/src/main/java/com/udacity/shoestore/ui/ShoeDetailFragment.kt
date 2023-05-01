package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.adapter.IconAdapter
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeDetailFragment: Fragment() {
    lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeListViewModel
    private lateinit var iconAdapter: IconAdapter

    private var shoeIndex: Int = -1
    private var clickedIcon: Int? = null

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

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)

        toolbar.title = "Shoe detail"

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding.buttonSave.setOnClickListener {
            if (saveShoe()) {
                findNavController().navigate(R.id.action_shoeDetail_to_shoeListFragment)
            }
        }
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_shoeDetail_to_shoeListFragment)
        }

        // Set up icon recyclerviewer
        val shoeIcons = listOf(
            R.drawable.sneakers,
            R.drawable.running,
            R.drawable.boots,
            R.drawable.heels
        )

        iconAdapter = IconAdapter(shoeIcons) { clickedIconId ->
            onIconSelected(clickedIconId)
        }
        binding.iconRecylerView.adapter = iconAdapter
        binding.iconRecylerView.layoutManager = GridLayoutManager(requireContext(), 4)
        return binding.root
    }

    private fun onIconSelected(clickedIconId: Int) {
        clickedIcon = clickedIconId

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

            // Set the icon for existing shoes
            it.shoeIcon.let { iconResourceId ->
                iconAdapter.selectIcon(iconResourceId)
            }
        }
    }

    private fun saveShoe(): Boolean {
        return if (validateAllFields()) {
            val shoe = createShoe()
            if (shoeIndex == -1) {
                viewModel.addShoe(shoe)
            } else {
                viewModel.editShoe(shoeIndex, shoe)
            }
            true
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields before saving!", Toast.LENGTH_SHORT).show()
            false
        }

    }

    private fun createShoe(): Shoe {
        val shoeName = binding.editShoeDetail.text.toString()
        val shoeSize = binding.editShowSize.text.toString().toDouble()
        val shoeCompany = binding.editCompany.text.toString()
        val shoeDesc = binding.editShoeDescription.text.toString()
        val shoeIcon = clickedIcon

        return Shoe(shoeName, shoeSize, shoeCompany, shoeDesc, shoeIcon ?: R.drawable.sneakers)
    }

    private fun validateAllFields(): Boolean {
        return (binding.editShoeDetail.text.isNotEmpty() &&
                binding.editShowSize.text.isNotEmpty() &&
                binding.editCompany.text.isNotEmpty() &&
                binding.editShoeDescription.text.isNotEmpty() &&
                clickedIcon != null)
    }
}