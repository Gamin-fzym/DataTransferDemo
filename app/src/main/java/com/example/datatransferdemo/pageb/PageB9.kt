package com.example.datatransferdemo.pageb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datatransferdemo.Dog
import com.example.datatransferdemo.R

class PageB9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b9)

        val dog = intent.getSerializableExtra("USER_KEY") as? Dog
        dog?.let {
            // 使用user对象
            Log.d("TransferTag", "name=${dog.name}, age=${dog.age}")
        }
    }
}