package com.example.fragmentdynamic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentdynamic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),Fragment1.OnMessageSendListener, Fragment2.OnMessageSendListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            loadFrag1()
        }

        binding.btn2.setOnClickListener {
            loadFrag2()
        }

    }

    fun loadFrag1(){
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView1, Fragment1.newInstance(), "frag1").commit()
    }

    fun loadFrag2(){
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView2, Fragment2.newInstance(), "frag2").commit()
    }

    override fun onMessageSend1(message: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? Fragment2
        fragment?.updateMessage(message)
    }

    override fun onMessageSend2(message: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView1) as? Fragment1
        fragment?.updateMessage(message)
    }


}