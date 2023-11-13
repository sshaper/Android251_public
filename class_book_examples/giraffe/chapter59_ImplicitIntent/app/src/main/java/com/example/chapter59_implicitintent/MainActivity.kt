package com.example.chapter59_implicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.net.Uri
import android.provider.Settings
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showWebPage(view: View){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ebookfrenzy.com"))
        startActivity(intent)

    }

    fun enableLink(view: View){
        val intent = Intent(Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS, Uri.parse("package:com.ebookfrenzy.mywebview"))
        startActivity(intent)
        //Log.i("zzz","it works")
    }
}