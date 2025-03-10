package com.example.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationdemo.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.secondFragBtn.setOnClickListener {
            val action: SecondFragmentDirections.SecondFragmentToThirdFragment = SecondFragmentDirections.secondFragmentToThirdFragment()
            action.image = R.drawable.bot
            action.message2 = binding.secondFragTextview.text.toString()
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun onStart(){
        super.onStart()
        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
            binding.secondFragTextview.text = args.message1
        }
    }

}
