package com.example.multifragmentnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri
import com.example.multifragmentnavigation.databinding.FragmentThirdBinding



class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //REPLACED WITH BINDING
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    interface OnFragmentInteractionListener{
        fun onFragment3Interaction(uri: Uri)
    }

    override fun onStart(){
        super.onStart()
        arguments?.let {
            val args = ThirdFragmentArgs.fromBundle(it)
                binding.textViewName.text = args.message1
            }
    }


}