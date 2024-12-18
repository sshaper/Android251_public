package com.example.productdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productdatabase.databinding.ProductListItemBinding  // Replace with your actual package name

class ProductListAdapter(private val productItemLayout: Int) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var productList: List<Product>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val product = productList?.get(listPosition)
        holder.binding.productRow.text = product?.productName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun setProductList(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    class ViewHolder(val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root)
}

