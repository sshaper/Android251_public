package com.example.chapter48_navigationv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri

class MainActivity : AppCompatActivity(), SecondFragment.OnFragmentInteractionListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //THEY HAVE THIS IN THE BOOK BUT I REMOVED IT AND IT DID NOT
    //MAKE A DIFFERENCE
    override fun onFragmentInteraction(uri: Uri) {
    }
}