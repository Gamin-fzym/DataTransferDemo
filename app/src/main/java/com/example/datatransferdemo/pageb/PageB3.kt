package com.example.datatransferdemo.pageb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datatransferdemo.R
import com.example.datatransferdemo.StaticDataHolder

class PageB3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b3)

        val receivedData = StaticDataHolder.sharedData
        Log.d("TransferTag", receivedData.toString())
        receivedData["content"]?.let { Log.d("TransferTag", it) }
    }
}