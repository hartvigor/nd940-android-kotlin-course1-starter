package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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
    ): View {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.lifecycleOwner = this

        binding.actionButtonShoelist.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_shoeListFragment_to_shoeDetail)
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
                shoesBinding.shoeName.text = it.name
                shoesBinding.shoeSize.text = it.size.toString()
                shoesBinding.shoeCompany.text = it.company
                shoesBinding.shoeDescription.text = it.description

                /**
                 * The clicklistener needs to be set on each child view in
                 * the scrollview insted of directly on the scrollview because of the
                 * databinding to each view
                 */

                shoesBinding.root.setOnClickListener {v ->
                    val clickIndex = binding.shoeListLayout.indexOfChild(v)
                    editClickedShoe(clickIndex)
                }

                binding.shoeListLayout.addView(shoesBinding.root)
            }
            if (shoeList.isEmpty()) loadShoe()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun editClickedShoe(index: Int) {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetail()
        val bundle = Bundle().apply {
            putInt("shoeIndex", index)
        }
        findNavController().navigate(action.actionId, bundle)
    }

    private fun loadShoe() {
        viewModel.addShoe(Shoe(name = "Air", size = 42.0, company = "Nike", description = "Nice shoes! "))
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

}