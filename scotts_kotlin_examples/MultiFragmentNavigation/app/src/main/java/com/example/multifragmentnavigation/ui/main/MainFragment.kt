package com.example.multifragmentnavigation.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.multifragmentnavigation.R
import com.example.multifragmentnavigation.databinding.MainFragmentBinding
import androidx.navigation.Navigation

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        //return inflater.inflate(R.layout.main_fragment, container, false)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.button1.setOnClickListener {
            //Navigation.findNavController(it).navigate(
                //R.id.action_mainFragment_to_secondFragment
            //)
            val action1: MainFragmentDirections.ActionMainFragmentToSecondFragment = MainFragmentDirections.actionMainFragmentToSecondFragment()
            action1.setMessage1(binding.name.text.toString())
            Navigation.findNavController(it).navigate(action1)
        }

        binding.button2.setOnClickListener {
            val action2: MainFragmentDirections.ActionMainFragmentToThirdFragment = MainFragmentDirections.actionMainFragmentToThirdFragment()
            action2.setMessage2(binding.name.text.toString())
            Navigation.findNavController(it).navigate(action2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}