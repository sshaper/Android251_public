package com.ebookfrenzy.sharedflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log

import com.ebookfrenzy.sharedflowdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "SharedFlowDemo"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val itemList = ArrayList<String>()
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        collectFlow()
    }

    fun collectFlow() {
        listAdapter = ListAdapter(itemList)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = listAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sharedFlow.collect { value ->
                    Log.i(TAG, "Collecting $value")
                    itemList.add(value.toString())
                    listAdapter.notifyItemInserted(itemList.lastIndex)
                    binding.recyclerView.smoothScrollToPosition(listAdapter.itemCount)
                }
            }
        }
    }
}