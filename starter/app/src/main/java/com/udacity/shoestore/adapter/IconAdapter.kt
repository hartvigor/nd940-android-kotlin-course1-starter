package com.udacity.shoestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R

class IconAdapter(val icons: List<Int>, val shoeIconClick: (Int) -> Unit): RecyclerView.Adapter<IconAdapter.ShoeIconViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeIconViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoe_icon_item, parent, false)
        return ShoeIconViewHolder(view, shoeIconClick)
    }

    override fun onBindViewHolder(holder: ShoeIconViewHolder, position: Int) {
        holder.bind(icons[position])
    }

    override fun getItemCount() = icons.size

    // Keep track of clicked shoe position in recycler viewer
    private var clickedShoePosition: Int = -1

    fun selectIcon(iconResourceId: Int) {
        val newPosition = icons.indexOf(iconResourceId)
        if (newPosition != -1) {
            val oldPosition = clickedShoePosition
            clickedShoePosition = newPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(newPosition)
        }
    }

    inner class ShoeIconViewHolder(
        itemView: View,
        private val itemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.icon_image_view)

        fun bind(iconResId: Int) {
            iconImageView.setImageResource(iconResId)

            // Set the shape of the selected icon in the recyclerviewer
            if (clickedShoePosition == adapterPosition) {
                itemView.setBackgroundResource(R.drawable.icon_clicked_shape)
            } else {
                itemView.background = null
            }

            itemView.setOnClickListener {
                // Setup for behavuour for cliking an icon
                val priviousClickedShoeItem = clickedShoePosition
                clickedShoePosition = adapterPosition
                notifyItemChanged(priviousClickedShoeItem)
                notifyItemChanged(clickedShoePosition)

                itemClickListener(iconResId)
            }
        }
    }
}