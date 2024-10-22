package com.example.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationdemo.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart(){
        super.onStart()
        arguments?.let {
            val args = ThirdFragmentArgs.fromBundle(it)
            binding.thirdFragTextview.text = args.message2
            binding.thirdFragImage.setImageResource(args.image)
        }
    }
}