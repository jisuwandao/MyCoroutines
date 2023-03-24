package com.example.mycoroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mycoroutinesdemo.acts.MainActivity01
import com.example.mycoroutinesdemo.acts.MainActivity02
import com.example.mycoroutinesdemo.acts.MainActivity03
import com.example.mycoroutinesdemo.acts.MainActivity04
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

//class MainActivity : AppCompatActivity(), CoroutineScope {
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_01).setOnClickListener {
            startActivity(Intent(this, MainActivity01::class.java))
        }

        findViewById<Button>(R.id.btn_02).setOnClickListener {
            startActivity(Intent(this, MainActivity02::class.java))
        }

        findViewById<Button>(R.id.btn_03).setOnClickListener {
            startActivity(Intent(this, MainActivity03::class.java))
        }

        findViewById<Button>(R.id.btn_04).setOnClickListener {
            startActivity(Intent(this, MainActivity04::class.java))
        }
    }

    private fun testCoroutines() {
        // CoroutineScope(this)

        //runBlocking {  }

        //GlobalScope.launch {  }

        //val corouScope = CoroutineScope(coroutineContext)
        //corouScope.launch {  }

        /*CoroutineScope(coroutineContext).launch {

        }*/

        val mainScoroutine = MainScope()
        mainScoroutine.launch {  }
    }

//    override val coroutineContext: CoroutineContext
//        // get() = TODO("Not yet implemented")
//        get() = coroutineContext + Job()


}




















