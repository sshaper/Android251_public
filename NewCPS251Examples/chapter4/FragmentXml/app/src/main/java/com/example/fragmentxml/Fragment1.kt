package com.example.fragmentxml

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentxml.databinding.Fragment1Binding
class Fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!
    private var messageSendListener: OnMessageSendListener? = null

    interface OnMessageSendListener {
        fun onMessageSend1(message: String)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        messageSendListener = context as? OnMessageSendListener
            ?: throw RuntimeException("$context must implement OnMessageSendListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment using view binding
            _binding = Fragment1Binding.inflate(inflater, container, false)

            binding.frag1Btn.setOnClickListener{
                messageSendListener?.onMessageSend1(binding.frag1EditText.text.toString())
                //clear the text box
                binding.frag1EditText.text.clear()
        }
        return binding.root
    }

    fun updateMessage(message: String) {
        binding.frag1TextView.text = message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // It's important to null out the binding when the view is destroyed to avoid memory leaks.
        _binding = null
    }
}