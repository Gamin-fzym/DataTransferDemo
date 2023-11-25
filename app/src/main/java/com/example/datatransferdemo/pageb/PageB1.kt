package com.example.datatransferdemo.pageb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.datatransferdemo.R
import com.example.datatransferdemo.tag


class PageB1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b1)

        // 获取传递过来的值
        val value = intent.getStringExtra("key")
        Log.d("TransferTag", "PageB1 value=$value")

        // 离开回传数据
        val but = findViewById<Button>(R.id.button)
        but.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.tag = "PageB1"
            returnIntent.putExtra("result_key", "返回字符串")
            setResult(RESULT_OK, returnIntent)

            finish() // 结束当前Activity, 不一定要立即结束。
        }
    }
}