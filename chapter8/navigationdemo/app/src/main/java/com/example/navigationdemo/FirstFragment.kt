package com.example.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationdemo.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstFragBtn1.setOnClickListener {
            val action: FirstFragmentDirections.MainToSecondFragment = FirstFragmentDirections.mainToSecondFragment()
            action.message1 = binding.firstFragInput.text.toString()
            Navigation.findNavController(it).navigate(action)
        }

        binding.firstFragBtn2.setOnClickListener {
            val action: FirstFragmentDirections.MainToThirdFragment = FirstFragmentDirections.mainToThirdFragment()
            action.message2 = binding.firstFragInput.text.toString()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}