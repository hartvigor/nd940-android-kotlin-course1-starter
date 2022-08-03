package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.createLoginButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        binding.loginButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        return binding.root
    }

}