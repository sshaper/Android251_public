package com.ebookfrenzy.navigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri

import com.ebookfrenzy.navigationdemo.ui.main.MainFragment

class MainActivity : AppCompatActivity(), SecondFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

}