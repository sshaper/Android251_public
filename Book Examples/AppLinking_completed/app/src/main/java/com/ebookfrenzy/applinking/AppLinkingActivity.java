package com.ebookfrenzy.applinking;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AppLinkingActivity extends AppCompatActivity {

    public static final String LANDMARK_ID = "landmarkID";
    private static final String TAG = "AppIndexActivity";
    private EditText idText;
    private EditText titleText;
    private EditText descriptionText;
    private static final int PERSONAL = 1;
    private static final int PUBLIC = 0;
    MyDBHandler dbHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_linking);

        idText = findViewById(R.id.idText);
        titleText = findViewById(R.id.titleText);
        descriptionText = findViewById(R.id.descriptionText);

        dbHandler = new MyDBHandler(this, null, null, 1);
    }

    public void findLandmark(View view) {

        if (!idText.getText().equals("")) {
            Landmark landmark = dbHandler.findLandmark(idText.getText().toString());

            if (landmark != null) {

                Intent intent = new Intent(this, LandmarkActivity.class);
                String landmarkid = idText.getText().toString();
                intent.putExtra(LANDMARK_ID, landmarkid);
                startActivity(intent);

                /*
                Log.i(TAG, "Found landmark = " + landmark.getTitle());
                Uri uri = Uri.parse("http://example.com/landmarks/" + landmark.getID());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                */
            } else {
                titleText.setText("No Match");
            }
        }
    }

    public void addLandmark(View view) {

        Landmark landmark =
                new Landmark(idText.getText().toString(), titleText.getText().toString(),
                        descriptionText.getText().toString(), 1);

        dbHandler.addLandmark(landmark);
        idText.setText("");
        titleText.setText("");
        descriptionText.setText("");

    }
}
