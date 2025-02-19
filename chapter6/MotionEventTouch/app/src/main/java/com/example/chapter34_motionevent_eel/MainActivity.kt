package com.example.chapter34_motionevent_eel


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet.Motion


import com.example.chapter34_motionevent_eel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")//this gets rid of accessibility warning
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //not needed
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.root.setOnTouchListener {_, m: MotionEvent ->
            handleTouch(m)
            true
        }


    }

    private fun handleTouch(m: MotionEvent)
    {
        val pointerCount = m.pointerCount

        for (i in 0 until pointerCount)
        {
            val x = m.getX(i)
            val y = m.getY(i)
            val id = m.getPointerId(i)
            val action = m.actionMasked
            val actionIndex = m.actionIndex


            //this is a recommended action from Android
            val actionString: String = when (action) {
                MotionEvent.ACTION_DOWN -> "DOWN"
                MotionEvent.ACTION_UP -> "UP"
                MotionEvent.ACTION_POINTER_DOWN -> "PNTR DOWN"
                MotionEvent.ACTION_POINTER_UP -> "PNTR UP"
                MotionEvent.ACTION_MOVE -> "MOVE"
                else -> ""
            }


            val touchStatus =
                "Pointer count: $pointerCount Action: $actionString Index: $actionIndex ID: $id X: $x Y: $y"

            if (id == 0)
                binding.textView1.text = touchStatus
            else
                binding.textView2.text = touchStatus
        }
    }
}