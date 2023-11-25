package com.example.datatransferdemo.pageb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datatransferdemo.R
import com.example.datatransferdemo.tag

class PageB2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_b2)

        // 获取传递过来的值
        val bundle = intent.extras
        val receivedID = bundle?.getInt("id") // 根据传递的数据类型使用对应的 getXXX() 方法
        val receivedStatus = bundle?.getBoolean("status")
        val receivedContent = bundle?.getString("content")
        Log.d("TransferTag", "PageB2 receivedContent=$receivedContent, receivedID=$receivedID, receivedStatus=$receivedStatus")

        // 离开回传数据
        val returnBundle = Bundle()
        returnBundle.putInt("id",123)
        returnBundle.putBoolean("status",true)
        returnBundle.putString("content", "传递字符串")
        val returnIntent = Intent()
        returnIntent.tag = "PageB2"
        returnIntent.putExtras(returnBundle)
        setResult(RESULT_OK, returnIntent)
    }
}