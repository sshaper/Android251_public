package com.example.multifragmentnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multifragmentnavigation.ui.main.MainFragment
import android.net.Uri

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

    }

    //override fun onFragmentInteraction(uri: Uri){

    //}
}