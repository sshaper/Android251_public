package com.ebookfrenzy.sqldemoclient

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.net.Uri
import android.view.View

import com.ebookfrenzy.sqldemoclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun reload(view: View?) {
        val cursor = contentResolver.query(
            Uri.parse(
                "content://com.ebookfrenzy.sqldemo.provider.MyContentProvider/customers"),
            null, null, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val stringBldr = StringBuilder()

                while (!cursor.isAfterLast) {
                    val nameindex = cursor.getColumnIndex("customername")
                    val phoneindex = cursor.getColumnIndex("customerphone")

                    if ((nameindex != -1) && (phoneindex != -1)) {
                        val string = cursor.getString(nameindex) + " " +
                                cursor.getString(phoneindex)

                        stringBldr.append("""
                            
                            ${string}
                            
                        """.trimIndent())
                        binding.textView.text = stringBldr
                    }
                    cursor.moveToNext()
                }
            }
            cursor.close()
        }
    }
}