package com.example.mycoroutinesdemo.test

import kotlin.coroutines.suspendCoroutine

class TestCoroutine {

    suspend fun main() {
        println("before")

        /*suspendCoroutine<Unit> { continuation ->
            continuation.resumeWith(Result<Unit>)
        }*/

        println("after")
    }
}