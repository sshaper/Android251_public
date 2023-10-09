package com.example.dynamicfragmentv2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dynamicfragmentv2.databinding.Fragment1Binding

class Fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    var activityCallback: Frag1Listener? = null

    interface Frag1Listener {
        fun onButtonClick(text: String)
    }

    companion object {
        fun newInstance() = Fragment1().apply { }
    }

    //THIS IS NEEDED BECAUSE FRAGMENT1 HAS A BUTTON ACTION THAT WILL BE SENDING DATA TO THE MAIN ACTIVITY,
    // WHERE AS FRAGMENT 2 ONLY RECIEVES THE DATA.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityCallback = context as Frag1Listener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement Frag1Listener"
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*THE FOLLOWING CODE WAS DEPRECATED
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frag1Btn.setOnClickListener {
                v: View -> buttonClicked(v)
        }
    }

    //WHEN THE BUTTON IS CLICKED I GET THE CONTENT FROM THE BOX AND PASS IT AS A PARAMETER
    //TO THE ONBUTTONCLICK METHOD LOCATED IN THE MAINACTIITY
    private fun buttonClicked(view: View) {
        var content = binding.frag1EditText.text.toString()
        activityCallback?.onButtonClick(content)
    }


}