package com.scottshaper.dolphintest.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.scottshaper.dolphintest.R

import com.scottshaper.dolphintest.databinding.FragmentMainBinding
import com.scottshaper.dolphintest.BR.myViewModel

class MainFragment : Fragment() {

    //private var _binding: FragmentMainBinding? = null
    //private val binding get() = _binding!!



    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.fragment_main, container, false)
        //_binding = FragmentMainBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }


    //ONACTIVITYCREATED IS DEPRECATED INSTEAD ONVIEWCREATED IS USED. HOWEVER IN THE DATABINDING YOU JUST NEED THE BINDING.SETVARIABLE... LINE


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(myViewModel, viewModel)
        Log.d("zzzz","please work")
        //binding.resultText.text = viewModel.getResult().toString()

        //THIS IS REMOVED BECAUSE WE REPLACE IT WITH DATABINDING
/*
        val resultObserver = Observer<Float> {
                result -> binding.resultText.text = result.toString()
        }

        viewModel.getResult().observe(viewLifecycleOwner,resultObserver)

        binding.convertButton.setOnClickListener {
            if (binding.dollarText.text.isNotEmpty()) {
                viewModel.setAmount(binding.dollarText.text.toString())
                //binding.resultText.text = viewModel.getResult().toString()
            } else {
                binding.resultText.text = "No Value"
            }
        }*/
    }

    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/



}