package com.example.background_service

import android.content.Intent //for launching the background service.
import io.flutter.embedding.android.FlutterActivity//for the main Android activity that hosts the Flutter app.
import io.flutter.embedding.engine.FlutterEngine//for managing the Flutter runtime.
import io.flutter.plugin.common.MethodChannel//for setting up a communication bridge between Flutter and Android.

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.example.background_service"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "startService" -> {
                    val intent = Intent(this, BackgroundService::class.java)
                    startService(intent)
                    result.success("Service Started")
                    moveTaskToBack(true)
                }
                "stopService" -> {
                    val intent = Intent(this, BackgroundService::class.java)
                    stopService(intent)
                    result.success("Service Stopped")
                }
 
                else -> result.notImplemented()
            }
        }
    }
}
