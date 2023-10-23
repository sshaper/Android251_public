package com.ebookfrenzy.fragmentexample

import androidx.fragment.app.FragmentActivity
import android.os.Bundle

import com.ebookfrenzy.fragmentexample.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(),
    ToolbarFragment.ToolbarListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onButtonClick(fontSize: Int, text: String) {
        val textFragment = supportFragmentManager.findFragmentById(
            R.id.text_fragment) as TextFragment

        textFragment.changeTextProperties(fontSize, text)
    }
}