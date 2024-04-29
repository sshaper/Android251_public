package com.ebookfrenzy.sqldemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View

import com.ebookfrenzy.sqldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHandler: MyDBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHandler = MyDBHandler(this, null, null, 1)
    }

    fun addCustomer(view: View) {
        val name = binding.customerName.text.toString()
        val phone = binding.customerPhone.text.toString()
        val customer = Customer(name, phone)

        dbHandler.addCustomer(customer)
        binding.customerName.setText("")
        binding.customerPhone.setText("")
    }

    fun findCustomer(view: View) {
        val customer =
            dbHandler.findCustomer(binding.customerName.text.toString())

        if (customer != null) {
            binding.customerPhone.setText(
                customer.customerPhone)
            binding.statusText.text = "Match Found"
        } else {
            binding.statusText.text = "No Match Found"
        }
    }

    fun deleteCustomer(view: View) {
        val result = dbHandler.deleteCustomer(
            binding.customerName.text.toString())
        if (result) {
            binding.statusText.text = "Record Deleted"
            binding.customerName.setText("")
            binding.customerPhone.setText("")
        } else
            binding.statusText.text = "No Match Found"
    }
}