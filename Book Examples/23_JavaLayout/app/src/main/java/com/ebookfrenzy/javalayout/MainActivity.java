package com.ebookfrenzy.javalayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;
import android.content.res.Resources;
import android.util.TypedValue;

import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureLayout();
    }

    private void configureLayout() {
        Button myButton = new Button(this);
        myButton.setText(getString(R.string.press_me));
        myButton.setBackgroundColor(Color.YELLOW);
        myButton.setId(R.id.myButton);

        EditText myEditText = new EditText(this);
        myEditText.setId(R.id.myEditText);

        int px = convertToPx(200);
        myEditText.setWidth(px);

        ConstraintLayout myLayout = new ConstraintLayout(this);
        myLayout.setBackgroundColor(Color.BLUE);

        myLayout.addView(myButton);
        myLayout.addView(myEditText);

        setContentView(myLayout);

        ConstraintSet set = new ConstraintSet();

        set.constrainHeight(myButton.getId(),
                ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(myButton.getId(),
                ConstraintSet.WRAP_CONTENT);

        set.connect(myButton.getId(), ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(myButton.getId(), ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(myButton.getId(), ConstraintSet.TOP,
                ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(myButton.getId(), ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.constrainHeight(myEditText.getId(),
                ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(myEditText.getId(),
                ConstraintSet.WRAP_CONTENT);

        set.connect(myEditText.getId(), ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        set.connect(myEditText.getId(), ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        set.connect(myEditText.getId(), ConstraintSet.BOTTOM,
                myButton.getId(), ConstraintSet.TOP, 70);

        set.applyTo(myLayout);

    }

    private int convertToPx(int value) {
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value,
                r.getDisplayMetrics());
        return px;
    }

}
