package com.example.chapter43DataBindingDemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

//live data imports
//import androidx.lifecycle.Observer
import com.example.chapter43DataBindingDemo.databinding.FragmentFirstBinding

//DATABINDING
import androidx.databinding.DataBindingUtil
import com.example.chapter43DataBindingDemo.BR.myViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    //private var _binding: FragmentFirstBinding? = null
    //private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentFirstBinding

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //_binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(myViewModel, viewModel)

        //binding.resultText.text = viewModel.getResult().toString()

        /*val resultObserver = Observer<Float>{
            result -> binding.resultText.text = result.toString()
        }

        viewModel.getResult().observe(viewLifecycleOwner, resultObserver)

        binding.convertButton.setOnClickListener{
            if (binding.dollarText.text.isNotEmpty()) {
                viewModel.setAmount(binding.dollarText.text.toString())
                //binding.resultText.text = viewModel.getResult().toString()
            } else {
                binding.resultText.text = "No Value"
            }
        }*/

        /*BOOK HAD US DELETE THIS CODE I COMMENTED IT OUT
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}