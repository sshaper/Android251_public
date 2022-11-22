package com.example.rest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.rest.api.TheCatApiService
import com.example.rest.model.ImageResultData
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private val agentBreedView: TextView by lazy {
        findViewById(R.id.main_agent_breed_value)
    }
    private val profileImageView: ImageView by lazy{
        findViewById(R.id.main_profile_image)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private val theCatApiService by lazy{
        retrofit.create(TheCatApiService::class.java)
    }
    private val imageLoader: ImageLoader by lazy {
        GlideImageLoader(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCatImageResponse()
    }
    private fun getCatImageResponse(){
        val call = theCatApiService.searchImages(1, "full")
        call.enqueue(object: Callback<List<ImageResultData>> {
            override fun onFailure(call: Call<List<ImageResultData>>, t: Throwable) {
                Log.e("MainActivity","Failed to get search results",t)
            }

            override fun onResponse(call: Call<List<ImageResultData>>, response: Response<List<ImageResultData>>) {
                if(response.isSuccessful){
                    val imageResults = response.body()
                    val firstImageUrl = imageResults?.firstOrNull()?.imageUrl ?: "No Url"
                    if(!firstImageUrl.isBlank()){
                        imageLoader.loadImage(firstImageUrl, profileImageView)
                    }
                    else {
                        Log.d("MainActivity","Missing image URL")
                    }

                    agentBreedView.text = imageResults?.firstOrNull()?.breeds?.firstOrNull()?.name ?: "Unknown"
                }
                else {
                    Log.e("MainActivity","Failed to get search results \n${response.errorBody()?.string() ?: ""}")
                }
            }

        })
    }
}