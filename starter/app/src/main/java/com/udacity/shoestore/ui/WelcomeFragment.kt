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
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)

        toolbar.title = "Welcome"

        binding.buttonInstructions.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_instructionFragment)
        }

        return binding.root
    }
}