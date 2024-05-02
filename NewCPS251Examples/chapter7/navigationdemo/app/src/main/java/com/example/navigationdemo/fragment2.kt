package com.example.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationdemo.databinding.Fragment2Binding

class fragment2 : Fragment() {

    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frag2Btn.setOnClickListener {
            val action: fragment2Directions.Fragment2ToFragment3 = fragment2Directions.fragment2ToFragment3()
             action.image = R.drawable.img
             action.message2 = binding.frag2Textview.text.toString()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val args = fragment2Args.fromBundle(it)
            binding.frag2Textview.text = args.message1
        }
    }
}