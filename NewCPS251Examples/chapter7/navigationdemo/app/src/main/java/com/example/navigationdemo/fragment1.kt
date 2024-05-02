package com.example.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationdemo.databinding.Fragment1Binding

class fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frag1Btn1.setOnClickListener {
            val action: fragment1Directions.MainToFragment2 = fragment1Directions.mainToFragment2()
            action.message1 = binding.frag1Input.text.toString()
            Navigation.findNavController(it).navigate(action)
        }

        binding.frag1Btn2.setOnClickListener {
            val action: fragment1Directions.MainToFragment3 = fragment1Directions.mainToFragment3()
            action.message2 = binding.frag1Input.text.toString()
            Navigation.findNavController(it).navigate(action)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}