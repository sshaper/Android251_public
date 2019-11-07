package com.ebookfrenzy.restgoogle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import com.google.gson.Gson;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.coupons_rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //CREATE AN INSTANCE OF GSON
        final Gson gson = new Gson();

        //CREATE AN INSTANCE OF REQUEST QUEUE
        RequestQueue queue = Volley.newRequestQueue(this);

        //GET THE URL STRING TO THE JASON FILE
        String url = "http://198.199.80.235/cps276/cps276_examples/datasources/coupons_json_251.json";

        // CREATES AN INSTANCE OF A JsonObjectRequest AND PASS IN SOME PARAMETERS
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                //WE ARE SENDING A GET REQUEST (WE ARE GETTING A FILE)
                Request.Method.GET,

                //THIS IS THE URL
                url,

                null,

                new Response.Listener<JSONObject>() {

                    //THIS LISTENS FOR THE RESPONSE
                    @Override
                    public void onResponse(JSONObject response) {

                        // USE METHOD fromJson TO DESERALIZE THE SPECIFIED JSON INTO AN OBJECT OF THE SPECIFIED CLASS
                        Coupons coupons = gson.fromJson(response.toString(), Coupons.class);

                        //GETS THE COUPON LIST FROM THE COUPONS CLASS AND PUTS IT INTO cpnlst AND ADDS IT AS A PARAMETER TO THE RecyclerViewAdaptor() CONSTRUCTOR
                        ArrayList<Coupons> cpnlst = coupons.getCouponList();
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(cpnlst);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //DO SOMETHING HERE WITH ERROR MESSAGE
                    }
                }
        );

        //ADDS OUR REQUEST TO THE QUE FOR PROCESSING.
        queue.add(jsonObjectRequest);
    }
}
