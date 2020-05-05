package com.example.hw6

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object ImageAnalyzer {

    @SuppressLint("UnsafeExperimentalUsageError")
    suspend fun getText(imageUri: Uri, context: Context): String? {
        return suspendCoroutine { continuation ->
            val image = FirebaseVisionImage.fromFilePath(context, imageUri)
            val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
            detector.processImage(image)
                .addOnSuccessListener { firebaseVisionText ->
                    continuation.resume(firebaseVisionText.text)
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }
}