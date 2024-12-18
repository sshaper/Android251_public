package com.example.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.recycleview.databinding.CardLayoutBinding

class RecyclerAdapter(private val data: Data) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            // Set the click listener on the itemView
            itemView.setOnClickListener { v ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) { // Check if position is valid
                    Snackbar.make(v, "Click detected on item $position", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
        }

        fun bind(title: String, detail: String, imageResId: Int) {
            binding.itemImage.setImageResource(imageResId)
            binding.itemTitle.text = title
            binding.itemDetail.text = detail
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind is referencing the bind function written above in the inner class
        holder.bind(data.titles[position], data.details[position], data.images[position])
    }

    override fun getItemCount(): Int = data.titles.size

}
