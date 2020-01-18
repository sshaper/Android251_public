package com.ebookfrenzy.transitiondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.view.View;
import android.transition.TransitionManager;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.animation.BounceInterpolator;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout = findViewById(R.id.myLayout);

        myLayout.setOnTouchListener(
                new ConstraintLayout.OnTouchListener() {
                    public boolean onTouch(View v,
                                           MotionEvent m) {
                        handleTouch();
                        return true;
                    }
                }
        );
    }

    public void handleTouch() {
        Button button = findViewById(R.id.myButton);

        Transition changeBounds = new ChangeBounds();
        changeBounds.setDuration(3000);
        changeBounds.setInterpolator(new BounceInterpolator());

        TransitionManager.beginDelayedTransition(myLayout,
                changeBounds);

        TransitionManager.beginDelayedTransition(myLayout);

        button.setMinimumWidth(500);
        button.setMinimumHeight(350);

        ConstraintSet set = new ConstraintSet();

        set.connect(R.id.myButton, ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);

        set.connect(R.id.myButton, ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);

        set.constrainWidth(R.id.myButton, ConstraintSet.WRAP_CONTENT);

        set.applyTo(myLayout);
    }

}
