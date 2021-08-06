package com.ebookfrenzy.restgoogle;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Context contx;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final TextView textView = findViewById(R.id.output);

        recyclerView = findViewById(R.id.coupons_rv);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final Gson gson = new Gson();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://198.199.80.235/cps276/cps276_examples/datasources/coupons_json_251.txt";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Object object = gson.fromJson(response.toString(), Object.class);
                        //textView.setText(response.toString());
                        //Log.d(TAG,"This is a test");
                        CouponsWrapper couponsWrapper= gson.fromJson(response.toString(), CouponsWrapper.class);

                        List<Coupon> cpnlst = couponsWrapper.getCouponList();

                        Log.e(TAG, "number of coupons from json response after gson parsing "+cpnlst.size());

                        CouponsRecyclerViewAdapter couponsRecyclerViewAdapter = new CouponsRecyclerViewAdapter(cpnlst, contx);
                        recyclerView.setAdapter(couponsRecyclerViewAdapter);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.toString());//textView.setText(error.toString());
                    }
                }
        );

        queue.add(jsonObjectRequest);
    }
}
