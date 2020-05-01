package com.example.hw6

import android.Manifest
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*
import java.io.File
import java.util.*


class CameraActivity : AppCompatActivity() {

    companion object {
        const val CAMERA_REQUEST_CODE = 0;
    }

    var cameraView: CameraView? = null
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CAMERA
                    )
                ) {
                    Toast.makeText(this, R.string.need_permission, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, R.string.permission_in_settings, Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun startCamera() {
        cameraView = findViewById(R.id.cameraView)
        cameraView?.captureMode = CameraView.CaptureMode.IMAGE
        cameraView?.bindToLifecycle(this as LifecycleOwner)
    }

    fun takePicture(view: View?) {
        val file = generatePictureFile()
        cameraView?.takePicture(
            file,
            AsyncTask.SERIAL_EXECUTOR,
            object: ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    job = GlobalScope.launch(context = Dispatchers.Main) {
                        addNote(file.absolutePath)
                        finish()
                    }
                }
                override fun onError(exception: ImageCaptureException) {
                    finish()
                }
            }
        )
    }

    private fun generatePictureFile() = File(filesDir, UUID.randomUUID().toString())

    suspend fun addNote(image: String) = withContext(Dispatchers.IO) {
        return@withContext App.noteRepository.addNote("My new note", image)
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
