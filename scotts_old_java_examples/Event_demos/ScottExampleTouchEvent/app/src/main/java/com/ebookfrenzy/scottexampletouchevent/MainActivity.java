package com.ebookfrenzy.scottexampletouchevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.support.constraint.ConstraintLayout;

import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int imgX;
    public int imgY;
    public int imgOffset;
    public ImageView image;
    public TextView outputText1;
    public TextView outputText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout myLayout = findViewById(R.id.main_activity);
        image = findViewById(R.id.androidImage);
        outputText1 = findViewById(R.id.outputText1);
        outputText2 = findViewById(R.id.outputText2);

        image.setX(439);
        image.setY(1000);
        imgX = 439;
        imgY = 1000;
        imgOffset = 230;


        myLayout.setOnTouchListener(
                new ConstraintLayout.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent m) {
                        handleTouch(m);
                        return true;
                    }
                }
        );
    }

    void handleTouch(MotionEvent m){

        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            int action = m.getActionMasked();

            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    if(checkRect(imgOffset, imgX, imgY, x, y)){
                        outputText1.setText("Clicked inside of image.\nImage is located at imgX: " + imgX + ", imgY: " + imgY);
                    }
                    else {
                        outputText1.setText("Clicked outside of image.\nImage is located at imgX: " + imgX + ", imgY: " + imgY);
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    break;
                default:
                    //outputText.setText("Some other action");
            }

            outputText2.setText("Pointer is at X: " + x + ", Y: " + y);

            if(checkRect(imgOffset, imgX, imgY, x, y)){


               imgX = x;
               imgY = y;

               /*THIS MOVES THE IMAGE UP AND OVER TO THE LEFT SO THAT IS COVERS THE CLICK POINT OF X AND Y, MAKES THE CLICK POINT MORE
                IN THE CENTER.*/

               image.setX(x - (imgOffset /2));
               image.setY(y - (imgOffset /2));
            }

        }
    }

    public Boolean checkRect(int imgW, int imgX, int imgY, int x, int y){
        if(x < (imgX - imgW) ||  x > (imgX + imgW) || y < (imgY - imgW) || y > (imgY + imgW)){
            //X OR Y COORDINATES ARE OUT OF THE BOUNDED AREA FOR THE IMAGE
            return false;
        }
        else {
            //X OR Y COORDINATES ARE IN THE BOUNDED AREA FOR THE IMAGE
            return true;
        }
    }


}
