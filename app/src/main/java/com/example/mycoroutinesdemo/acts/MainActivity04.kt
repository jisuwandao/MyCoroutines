package com.example.mycoroutinesdemo.acts

import android.app.ProgressDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.mycoroutinesdemo.R
import kotlinx.coroutines.*

/**
 * 请求加载[用户数据]
 * suspend 就是一个提醒作用，提醒用户 当前的函数，是挂起函数，可能会执行异常操作，你注意下
 */
private suspend fun requestLoadUser() : String {
    val isLoadSuccess = true // 加载成功，和，加载失败，的标记

    // 此协程能够保证在异步执行而已
    withContext(Dispatchers.IO) {
        delay(3000L)  // 模拟请求服务器 所造成的耗时
    }

    if (isLoadSuccess) {
        return "加载到[用户数据]信息集"
    } else {
        return "加载[用户数据],加载失败,服务器宕机了"
    }
}

/**
 * 请求加载[用户资产数据]
 */
private suspend fun requestLoadUserAssets(): String {
    val isLoadSuccess = true // 加载成功，和，加载失败，的标记

    // 开启异步线程，去请求加载服务器的数据集
    withContext(Dispatchers.IO) {
        delay(3000L) // 模拟请求服务器 所造成的耗时
    }
    if (isLoadSuccess) {
        return "加载到[用户资产数据]信息集"
    } else {
        return "加载[用户资产数据],加载失败,服务器宕机了"
    }
}

/**
 * 传统方式完成异步任务网络加载
 */
class MainActivity04 : AppCompatActivity() {
    private lateinit var tvContent: TextView
    private lateinit var btn: Button

    private val TAG = "MainActivity02"
    private var mProgressDialog: ProgressDialog? = null

    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main01)

        tvContent = findViewById(R.id.tv_title)
        btn= findViewById(R.id.btn_01)
        btn.setOnClickListener { startRequest() }
    }

    // 按钮 点击事件
    private fun startRequest() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setTitle("请求服务器中...")
        mProgressDialog?.show()

        mainScope.launch(Dispatchers.Main) {

            // 第一步
            val responseResult =  requestLoadUser()
            // 更新UI
            tvContent.text = responseResult // 更新控件
            tvContent.setTextColor(Color.YELLOW)

            // 第二步
            val responseResult2 = requestLoadUserAssets()
            tvContent.text = responseResult2
            tvContent.setTextColor(Color.RED)

            mProgressDialog?.dismiss()
        }
    }

    override fun onDestroy() {
        mainScope?.cancel()
        super.onDestroy()
    }

}