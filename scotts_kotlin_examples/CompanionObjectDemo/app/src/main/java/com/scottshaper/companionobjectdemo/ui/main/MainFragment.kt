package com.scottshaper.companionobjectdemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.scottshaper.companionobjectdemo.R
import com.scottshaper.companionobjectdemo.Timer
import com.scottshaper.companionobjectdemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val resultObserver = Observer<Int> {
                result -> binding.number.text = result.toString()
        }
        viewModel.getSeconds().observe(viewLifecycleOwner, resultObserver)

        //ONCE THE BUTTON IN CLICKED IT CAN'T BE CLICKED AGAIN.  THIS WILL HIDE THE BUTTON WHEN THE SCREEN IS ROTATED (RE-DRAWN)
        if(viewModel.btn == false){
            binding.strTimerBtn.setVisibility(View.INVISIBLE)
        }

        //TO START THINGS OFF THE USER CLICKS THE BUTTON WHICH INSTATIATES THE TIMER CLASS AND STARTS THE TIMER.  THE MAIN FRAGMENT ALSO IS ATTACHED TO AN INSTANCE OF THE MAIN VIEW MODEL
       binding.strTimerBtn.setOnClickListener {
           var timer = Timer()
           timer.startTimer(0)
           //I SET THE BUTTON TO INVISIBLE AFTER ITS CLICKED SO IT CANNOT BE CLICKED AGAIN
           binding.strTimerBtn.setVisibility(View.INVISIBLE)
           viewModel.btn = false
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}