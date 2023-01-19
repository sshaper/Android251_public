package com.example.viewmodeldemolivedataname_eel.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.viewmodeldemolivedataname_eel.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        /*NAMEOBSERVER FIRES WHEN IT SEES THAT NAME HAS CHANGED AND THEN THAT HAPPENS IT PUT THE VALUE INTO THE TEXT FIELD*/
        val nameObserver = Observer<String> {
                name -> binding.nameoutput.text = name.toString()
        }

        //THIS IS ACTUALLY DOING THE OBSERVING
        viewModel.getName().observe(viewLifecycleOwner, nameObserver)

        //CLICKING THIS BUTTON STARTS THE WHOLE PROCESS
        binding.displayNamesBtn.setOnClickListener {
            viewModel.displayNames()
        }
    }


    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        /*NAMEOBSERVER FIRES WHEN IT SEES THAT NAME HAS CHANGED AND THEN THAT HAPPENS IT PUT THE VALUE INTO THE TEXT FIELD*/
        val nameObserver = Observer<String> {
                name -> binding.nameoutput.text = name.toString()
        }

        //THIS IS ACTUALLY DOING THE OBSERVING
        viewModel.getName().observe(viewLifecycleOwner, nameObserver)

        //CLICKING THIS BUTTON STARTS THE WHOLE PROCESS
        binding.displayNamesBtn.setOnClickListener {
            viewModel.displayNames()
        }
    }*/
}