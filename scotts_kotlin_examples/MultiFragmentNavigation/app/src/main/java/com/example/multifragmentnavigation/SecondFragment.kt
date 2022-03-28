package com.example.multifragmentnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.multifragmentnavigation.databinding.FragmentSecondBinding
import com.example.multifragmentnavigation.ui.main.MainFragmentDirections



class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //REPLACED WITH BINDING
         // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)
         _binding = FragmentSecondBinding.inflate(inflater, container, false)
          return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button3.setOnClickListener {
            //Navigation.findNavController(it).navigate(
            //R.id.action_mainFragment_to_secondFragment
            //)
            val action: SecondFragmentDirections.ActionSecondFragmentToThirdFragment = SecondFragmentDirections.actionSecondFragmentToThirdFragment()
            action.setMessage1(binding.textViewName.text.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }


    /*interface OnFragmentInteractionListener{
        fun onFragmentInteraction(uri: Uri)
    }*/

    override fun onStart(){
        super.onStart()
        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
            binding.textViewName.text = args.message1

        }
    }


}