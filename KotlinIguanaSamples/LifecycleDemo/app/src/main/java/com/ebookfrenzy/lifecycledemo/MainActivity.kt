package com.ebookfrenzy.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge

import com.ebookfrenzy.lifecycledemo.DemoObserver
import com.ebookfrenzy.lifecycledemo.DemoOwner

class MainActivity : AppCompatActivity() {

    private lateinit var demoOwner: DemoOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lifecycle.addObserver(DemoObserver())
        demoOwner = DemoOwner(this.lifecycle)
        demoOwner.startOwner()
        demoOwner.stopOwner()
    }
}
