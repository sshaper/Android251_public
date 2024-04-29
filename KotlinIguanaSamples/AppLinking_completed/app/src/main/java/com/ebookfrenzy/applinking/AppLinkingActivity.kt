package com.ebookfrenzy.applinking

import android.net.Uri
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.ebookfrenzy.applinking.databinding.ActivityAppLinkingBinding

class AppLinkingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppLinkingBinding
    private var dbHandler: MyDBHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAppLinkingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHandler = MyDBHandler(this, null, null, 1)
    }

    fun findLandmark(view: View) {

        if (binding.idText.text.toString() != "") {
            val landmark = dbHandler?.findLandmark(binding.idText.text.toString())

            if (landmark != null) {

                val intent = Intent(this, LandmarkActivity::class.java)
                val landmarkid = binding.idText.text.toString()
                intent.putExtra(LANDMARK_ID, landmarkid)
                startActivity(intent)

                /*
                val uri = Uri.parse("http://example.com/landmarks/" + landmark.id);
                val intent = Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                */
            } else {
                binding.titleText.setText("No Match")
            }
        }
    }

    fun addLandmark(view: View) {

        val landmark = Landmark(binding.idText.text.toString(), binding.titleText.text.toString(),
                binding.descriptionText.text.toString(), 1)

        dbHandler?.addLandmark(landmark)
        binding.idText.setText("")
        binding.titleText.setText("")
        binding.descriptionText.setText("")

    }

    companion object {

        val LANDMARK_ID = "landmarkID"
        private val TAG = "AppIndexActivity"
        private val PERSONAL = 1
        private val PUBLIC = 0
    }
}
