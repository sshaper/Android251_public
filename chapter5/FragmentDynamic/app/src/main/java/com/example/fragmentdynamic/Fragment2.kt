package com.example.fragmentdynamic


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdynamic.databinding.Fragment2Binding

class Fragment2 : Fragment() {

    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!
    private var messageSendListener: OnMessageSendListener? = null

    interface OnMessageSendListener {
        fun onMessageSend2(message: String)
    }

    //The companion object has to be used so the main activity can load the fragment
    companion object {
        fun newInstance() = Fragment2().apply { }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        messageSendListener = context as? OnMessageSendListener
            ?: throw RuntimeException("$context must implement OnMessageSendListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment using view binding
        _binding = Fragment2Binding.inflate(inflater, container, false)

        binding.frag2Btn.setOnClickListener{
            messageSendListener?.onMessageSend2(binding.frag2EditText.text.toString())

            //clear the text box
            binding.frag2EditText.text.clear()
        }

        return binding.root
    }

    fun updateMessage(message: String) {
        binding.frag2TextView.text = message
    }
}