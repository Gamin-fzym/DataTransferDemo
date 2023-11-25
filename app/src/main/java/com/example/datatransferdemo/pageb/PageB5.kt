package com.example.datatransferdemo.pageb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datatransferdemo.MainActivity
import com.example.datatransferdemo.R

class PageB5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b5)

        // 调用DataCallback的方法
        if (MainActivity.callbackInstance != null) {
            MainActivity.callbackInstance?.onDataReceived("传递的数据")
        }

    }
}