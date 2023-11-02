package com.example.chapter55_carddemobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter55_carddemobasic.databinding.ActivityMainBinding
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        layoutManager = LinearLayoutManager(this)
        //binding.contentMain.recyclerView.layoutManager = layoutManager
        binding.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        //binding.contentMain.recyclerView.adapter = adapter
        binding.recyclerView.adapter = adapter


    }
}