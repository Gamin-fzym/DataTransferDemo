package com.example.datatransferdemo.pageb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datatransferdemo.MyApp
import com.example.datatransferdemo.R

class PageB7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b7)

        val myApp = application as MyApp
        val receivedData = myApp.sharedData
        Log.d("TransferTag", receivedData)
    }
}