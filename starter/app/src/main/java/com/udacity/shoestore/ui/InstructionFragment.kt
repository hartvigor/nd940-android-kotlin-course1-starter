package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)

        toolbar.title = "Instruction"

        binding.buttonInstructionToShoeList.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(R.id.action_instructionFragment_to_shoeListFragment)
        }


        return binding.root
    }

}