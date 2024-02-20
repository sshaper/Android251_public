package com.example.chapter71_roomdemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter71_roomdemo.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: ProductListAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenerSetup()
        observerSetup()
        recyclerSetup()
    }

    private fun clearFields() {
        binding.productID.text = ""
        binding.productName.setText("")
        binding.productQuantity.setText("")
    }

    private fun listenerSetup() {
        binding.addButton.setOnClickListener {
            val name = binding.productName.text.toString()
            val quantity = binding.productQuantity.text.toString()

            if (name != "" && quantity != "") {
                val product = Product(name, Integer.parseInt(quantity))
                viewModel.insertProduct(product)
                clearFields()
            } else {
                binding.productID.text = "Incomplete information"
            }
        }
        binding.findButton.setOnClickListener {
            viewModel.findProduct(
            binding.productName.text.toString())
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteProduct(binding.productName.text.toString())
            clearFields()
        }
    }



    private fun recyclerSetup() {
        adapter = ProductListAdapter(R.layout.product_list_item)
        binding.productRecycler.layoutManager = LinearLayoutManager(this)
        binding.productRecycler.adapter = adapter
    }

}