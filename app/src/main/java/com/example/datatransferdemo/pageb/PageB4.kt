package com.example.datatransferdemo.pageb

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferdemo.R

class PageB4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b4)

        val sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val receivedData = sharedPref.getString("key", "") // 根据传递的数据类型使用对应的 getXXX() 方法
        if (receivedData != null) {
            Log.d("TransferTag", receivedData)
        }
    }
}