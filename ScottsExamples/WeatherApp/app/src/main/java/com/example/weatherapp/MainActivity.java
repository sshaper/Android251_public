package com.example.weatherapp;
//HERE IS AN EXAMPLE OF THE API
//http://api.openweathermap.org/data/2.5/weather?zip=48843&unit=metric&APPID=80d537a4b4cd7a3b10a3c65a70316965

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    //THIS WAS USED FOR LOGCAT
    private String TAG = "MyActivity";
    public TextView dateLocationSentence;
    public TextView temp;
    public TextView tempRange;
    public TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateLocationSentence = findViewById(R.id.dateLocationSentence);
        temp = findViewById(R.id.tempView);
        tempRange = findViewById(R.id.tempRange);
        description = findViewById(R.id.description);
    }

    //I CREATED THIS METHOD FOR THE BUTTON CLICK WHICH JUST CALLS ANOTHER METHOD THAT STARTS THE PROCESS. i HAD TO DO THIS BECAUSE ON RESTORE INSTANCE STATE I NEED TO CALL THE METHOD AGAIN AND I CANNOT CALL THIS ONE BECAUSE OF THE VIEW.
    public void getWeatherBtn(View v){
        this.getWeather();
    }

    //THIS METHOD STARTS THE PROCESS.  HERE I CHECK FOR A ZIP CODE AND THEN I CREATE THE URL AND SENT IT TO GETWEATHERDATA
    public void getWeather(){
        EditText editTextZip = findViewById(R.id.editTextZip);
        String zip = editTextZip.getText().toString();
        String url;

        if (zip.matches("[0-9]{5}")){
            url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zip + "&units=imperial&APPID=80d537a4b4cd7a3b10a3c65a70316965";
             getWeatherData(url);
        }
        else {
            Log.i(TAG,"Wrong Address");
        }
    }


    private void getWeatherData(String url){
        //HERE I DO THE VOLLEY REQUEST, NOTE THIS IS ALL DONE ON THE MAIN THREAD BUT I HAVE NO OTHER PROCESSES DO IT IS FINE.  PLUS VERY SMALL AMOUNT OF DATA
        RequestQueue queue = Volley.newRequestQueue(this);

        //HERE WE SEND A GET REQUEST TO THE SERVER AND GET A JSON OBJECT RESPONSE
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //HERE THE JSON IS PUT INTO A STRING AND SENT TO PARSEDISPLAYDATA
                        parseDisplayData(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        parseDisplayData("ERROR");
                    }
                }
        );

        queue.add(jsonObjectRequest);
    }

    //HERE WE PARSE THE DATA AND PUT IT IN THE APPROPRATE FIELDS.
    private void parseDisplayData(String json){

        if(json == "ERROR"){
            Log.i(TAG,"There was an error");
        }
        else {
            //THIS IS WHERE I WORK WITH THE OBJECT.
            Log.i(TAG,json);
            Gson gson = new Gson();
            //THE MAIN OBJECT CLASS IS THE MAIN CLASS FOR PARSING THE JSON.  GSON PUTS ALL SUB OBJECTS AND ARRAYS IN SEPERATE CLASSES.  BELOW IS AN EXAMPLE OF THE DATA RETURNED.  THE CLASSES HAVE TO BE THE SAME NAMES AS THE JSON KEYS.  I CREATED COORD, MAIN, SYS, WEATHERARRAY (NOTE WEATHERARRAY IS AN ARRAY THE REFERENCES A CLASS IN ITS LIST...SEE MAINOBJ.JAVA
            MainObj mainObj = gson.fromJson(json, MainObj.class);

            dateLocationSentence.setText(mainObj.getDate());
            temp.setText(mainObj.displayTemp());
            tempRange.setText(mainObj.displayTempRange());
            description.setText(mainObj.weatherDescription());
         }
    }

    //ALL I DID HERE WAS SET A TEXT STRING IF THE BUTTON WAS CLICKED.  THAT WAY ON ROTATION IT WOULD KNOW TO RECALL THE MAIN FUNCTION.  I COULD HAVE SAVED EACH PART OF THE TEXT BUT I FELT SINCE IT IS SUCH A SMALL CALL I WOULD JUST RECALL THE DATA.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        CharSequence userText = "buttonClicked";
        outState.putCharSequence("savedText", userText);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CharSequence userText =  savedInstanceState.getCharSequence("savedText");
        if(userText == "buttonClicked"){
            this.getWeather();
        }
    }
}

/*
***********THIS IS A SAMPLE RETURNED FROM THE WEATHER API**************************
{"coord":{"lon":-83.92,"lat":42.62},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13d"}],"base":"stations","main":{"temp":25.5,"feels_like":15.49,"temp_min":23,"temp_max":28.4,"pressure":1011,"humidity":85},"visibility":1207,"wind":{"speed":9.17,"deg":360,"gust":24.16},"snow":{"1h":0.25},"clouds":{"all":90},"dt":1582753539,"sys":{"type":1,"id":5267,"country":"US","sunrise":1582719425,"sunset":1582759233},"timezone":-18000,"id":0,"name":"Howell","cod":200}
 */
