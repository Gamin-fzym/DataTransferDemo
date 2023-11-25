package com.example.datatransferdemo.pageb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferdemo.R

class PageB11 : AppCompatActivity() {
    private lateinit var fileHelper: FileHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b11)

        fileHelper = FileHelper(this)

        // 读取数据
        val sharedData = fileHelper.readFromFile("shared_data.txt")
        // 使用数据
        Log.d("TransferTag", "sharedData=$sharedData")
    }
}