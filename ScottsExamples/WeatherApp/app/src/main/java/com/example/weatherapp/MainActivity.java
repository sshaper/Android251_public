package com.example.weatherapp;

//http://api.openweathermap.org/data/2.5/weather?zip=48843&APPID=80d537a4b4cd7a3b10a3c65a70316965

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MyActivity";
    //private String scott = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeather(View v){
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
        //String url = "http://api.openweathermap.org/data/2.5/weather?zip=48843&APPID=80d537a4b4cd7a3b10a3c65a70316965";
    }

    private void parseDisplayData(String json){

        if(json == "ERROR"){
            Log.i(TAG,"There was an error");
        }
        else {
            Log.i(TAG,json);
        }
    }

    private void getWeatherData(String url){

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Object object = gson.fromJson(response.toString(), Object.class);
                        //textView.setText(response.toString());

                        //Log.i(TAG, "works here");
                        //Log.i(TAG, response.toString());
                        //scott = response.toString();
                        parseDisplayData(response.toString());

                        //return response.toString();
                        //NamesWrapper namesWrapper = gson.fromJson(response.toString(), NamesWrapper.class);

                        //List<Name> namelst = namesWrapper.getNameList();

                        //Log.e(TAG, "number of coupons from json response after gson parsing "+namelst.size());

                        //NamesRecyclerViewAdapter couponsRecyclerViewAdapter = new NamesRecyclerViewAdapter(namelst, contx);
                        //recyclerView.setAdapter(couponsRecyclerViewAdapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.i(TAG, error.toString());//textView.setText(error.toString());
                        parseDisplayData("ERROR");
                    }
                }
        );

        queue.add(jsonObjectRequest);
    }
}
