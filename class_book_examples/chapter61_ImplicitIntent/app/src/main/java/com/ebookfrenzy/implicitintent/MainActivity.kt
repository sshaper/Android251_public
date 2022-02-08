package com.ebookfrenzy.implicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.net.Uri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showWebPage(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ebookfrenzy.com"))

        //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wccnet.edu"))

        startActivity(intent)
    }
}