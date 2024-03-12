package com.example.chapter48_navigationv3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.net.Uri
import com.example.chapter48_navigationv3.databinding.FragmentFirstBinding
import com.example.chapter48_navigationv3.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onStart() {
        super.onStart()
        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
            binding.argsText.text = args.message

        }
    }



    }

