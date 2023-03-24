package com.example.mycoroutinesdemo.acts

import android.app.ProgressDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import com.example.mycoroutinesdemo.R


/**
 * 请求加载[用户数据]
 * @param responseCallback 请求加载[用户数据]后 回调给外界的接口
 */
private fun requestLoadUser(responseCallback: ResponseCallback) {
    val isLoadSuccess = true // 加载成功，和，加载失败，的标记

    // 开启异步线程，去请求加载服务器的数据集
    object:Thread() {
        override fun run() {
            super.run()
            try {
                sleep(3000L) // 模拟请求服务器 所造成的耗时

                if (isLoadSuccess) {
                    responseCallback.responseSuccess("加载到[用户数据]信息集")
                } else {
                    responseCallback.responseFail("加载[用户数据],加载失败,服务器宕机了")
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }.start()
}

/**
 * 请求加载[用户资产数据]
 * @param responseCallback 请求加载[用户资产数据]后 回调给外界的接口
 */
private fun requestLoadUserAssets(responseCallback: ResponseCallback) {
    val isLoadSuccess = true // 加载成功，和，加载失败，的标记

    // 开启异步线程，去请求加载服务器的数据集
    object:Thread() {
        override fun run() {
            super.run()
            try {
                sleep(3000L) // 模拟请求服务器 所造成的耗时

                if (isLoadSuccess) {
                    responseCallback.responseSuccess("加载到[用户资产数据]信息集")
                } else {
                    responseCallback.responseFail("加载[用户资产数据],加载失败,服务器宕机了")
                }

            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }.start()
}

/**
 * 传统方式完成异步任务网络加载
 */
class MainActivity03 : AppCompatActivity() {
    private lateinit var tvContent: TextView
    private lateinit var btn: Button

    private val TAG = "MainActivity02"
    private var mProgressDialog: ProgressDialog? = null


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

        // TODO 先执行异步一
        requestLoadUser(object : ResponseCallback {
            override fun responseSuccess(responseData: String) {
                // TODO("Not yet implemented")
                val handler = object : Handler(Looper.getMainLooper()) {
                    override fun handleMessage(msg: Message) {
                        super.handleMessage(msg)
                        //更新UI
                        tvContent.text = responseData
                        tvContent.setTextColor(Color.YELLOW)

                        // TODO 先执行异步二
                        requestLoadUserAssets(object : ResponseCallback{
                            override fun responseSuccess(responseData: String) {
                                // TODO("Not yet implemented")
                                val handler = object : Handler(Looper.getMainLooper()) {
                                    override fun handleMessage(msg: Message) {
                                        super.handleMessage(msg)
                                        //更新UI
                                        tvContent.text = responseData
                                        tvContent.setTextColor(Color.RED)

                                        mProgressDialog?.dismiss()
                                    }
                                }

                                handler.sendEmptyMessage(0)
                            }

                            override fun responseFail(responseError: String) {
                                //TODO("Not yet implemented")
                            }

                        })

                    }
                }

                handler.sendEmptyMessage(0)

            }

            override fun responseFail(responseError: String) {
                //TODO("Not yet implemented")
            }

        })

    }

}

interface ResponseCallback{
    fun responseSuccess(responseData: String)
    fun responseFail(responseError: String)
}