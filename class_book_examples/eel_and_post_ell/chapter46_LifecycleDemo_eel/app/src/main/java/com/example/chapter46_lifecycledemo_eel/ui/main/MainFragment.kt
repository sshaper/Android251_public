package com.example.chapter46_lifecycledemo_eel.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chapter46_lifecycledemo_eel.R

//import com.example.chapter46_lifecycledemo_eel.DemoObserver
import com.example.chapter46_lifecycledemo_eel.DemoOwner

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    private lateinit var demoOwner: DemoOwner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        //lifecycle.addObserver(DemoObserver())

        //THESE METHODS ARE FOR THE CUSTOM CLASS.
        demoOwner = DemoOwner()
        demoOwner.startOwner()
        demoOwner.stopOwner()

    }

}