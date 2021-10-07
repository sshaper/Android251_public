package com.scottshaper.fragmentdemo


import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.scottshaper.fragmentdemo.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(), Fragment1.Frag1Listener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //I COMMENT THIS OUT INSTEAD OF DELETING SO I CAN UNDO EASILY
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn1.setOnClickListener {
            loadFrag1()
        }
        binding.btn2.setOnClickListener{
            loadFrag2()
        }
    }

    override fun onButtonClick(text: String) {
        val frag2 = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as Fragment2
        frag2.changeTextProperties(text)
     }

    fun loadFrag1(){
        //Article I used for a reference on supportFragmentManager
        // https://www.raywenderlich.com/1364094-android-fragments-tutorial-an-introduction-with-kotlin
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView1, Fragment1.newInstance(), "frag1").commit()
   }

    fun loadFrag2(){
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView2, Fragment2.newInstance(), "frag2").commit()

    }

}