package com.example.twowaydatabinding_eel.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.twowaydatabinding_eel.R
import com.example.twowaydatabinding_eel.databinding.FragmentMainBinding
import com.example.twowaydatabinding_eel.BR.myViewModel


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.setVariable(myViewModel, viewModel)

        return binding.root
    }
}