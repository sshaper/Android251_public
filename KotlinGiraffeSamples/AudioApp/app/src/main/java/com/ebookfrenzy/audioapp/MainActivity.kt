package com.ebookfrenzy.audioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Environment
import android.view.View
import android.media.MediaPlayer
import android.Manifest
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import java.io.File

import com.ebookfrenzy.audioapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null

    private var audioFilePath: String? = null
    private var isRecording = false
    private val RECORD_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        audioSetup()
    }

    private fun requestPermission(permissionType: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this,
            permissionType)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(permissionType), requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0]
                    != PackageManager.PERMISSION_GRANTED
                ) {

                    binding.recordButton.isEnabled = false

                    Toast.makeText(
                        this,
                        "Record permission required",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun audioSetup() {

        if (!hasMicrophone()) {
            binding.stopButton.isEnabled = false
            binding.playButton.isEnabled = false
            binding.recordButton.isEnabled = false
        } else {
            binding.playButton.isEnabled = false
            binding.stopButton.isEnabled = false
        }

        val audioFile = File(this.filesDir, "myaudio.3gp")
        audioFilePath = audioFile.absolutePath

        requestPermission(Manifest.permission.RECORD_AUDIO,
            RECORD_REQUEST_CODE)
    }

    fun recordAudio(view: View) {
        isRecording = true
        binding.stopButton.isEnabled = true
        binding.playButton.isEnabled = false
        binding.recordButton.isEnabled = false

        try {
            mediaRecorder = MediaRecorder(this)
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(
                MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder?.setOutputFile(audioFilePath)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder?.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mediaRecorder?.start()
    }

    fun stopAudio(view: View) {

        binding.stopButton.isEnabled = false
        binding.playButton.isEnabled = true

        if (isRecording) {
            binding.recordButton.isEnabled = false
            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder = null
            isRecording = false
        } else {
            mediaPlayer?.release()
            mediaPlayer = null
            binding.recordButton.isEnabled = true
        }
    }

    fun playAudio(view: View) {
        binding.playButton.isEnabled = false
        binding.recordButton.isEnabled = false
        binding.stopButton.isEnabled = true

        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(audioFilePath)
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }

    private fun hasMicrophone(): Boolean {
        val pmanager = this.packageManager
        return pmanager.hasSystemFeature(
            PackageManager.FEATURE_MICROPHONE)
    }
}