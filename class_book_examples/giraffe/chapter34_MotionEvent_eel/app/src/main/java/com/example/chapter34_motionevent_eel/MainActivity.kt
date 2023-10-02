package com.example.chapter34_motionevent_eel


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet.Motion


import com.example.chapter34_motionevent_eel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //not needed
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnTouchListener{_, m: MotionEvent -> handleTouch(m)
            true
        }

        //previous code does the same thing
        //binding.activityMain.setOnTouchListener {_, m: MotionEvent ->
            //handleTouch(m)
            //true
       // }
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
            var actionString: String

            when (action)
            {
                MotionEvent.ACTION_DOWN -> actionString = "DOWN"
                MotionEvent.ACTION_UP -> actionString = "UP"
                MotionEvent.ACTION_POINTER_DOWN -> actionString = "PNTR DOWN"
                MotionEvent.ACTION_POINTER_UP -> actionString = "PNTR UP"
                MotionEvent.ACTION_MOVE -> actionString = "MOVE"
                else -> actionString = ""
            }

            val touchStatus =
                "Action: $actionString Index: $actionIndex ID: $id X: $x Y: $y"

            if (id == 0)
                binding.textView1.text = touchStatus
            else
                binding.textView2.text = touchStatus
        }
    }
}