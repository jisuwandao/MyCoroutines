package com.example.mycoroutinesdemo.sample

/*class Samples {

    fun main() = CoroutineScope(Dispatchers.Main).launch {
        println("label 0")
        val isLogin = checkLogin() // suspend

        println("label 1")
        println("$isLogin")
        val login = login() // suspend

        println("label 2")
        println("$login")
        val id = getId() // suspend

        println("label 3")
        println("$id")
    }

    *//*private fun getId(): Any {
        TODO("Not yet implemented")
    }

    private fun login(): Any {
        TODO("Not yet implemented")
    }

    private fun checkLogin(): Any {
        TODO("Not yet implemented")
    }*//*

}*/



/*final class KotlinTest$main$1 extends SuspendLambda implements Function2 {
    int label = 0;  // 状态码
    public final Object invokeSuspend(Object result) {
        switch(this.label) {
            case 0:
            println("label 0");
            label = 1;
            result = checkLogin(this); // this 是编译器添加的续体参数
            if (result == COROUTINE_SUSPENDED) {
                return COROUTINE_SUSPENDED;
            }
            break;
            case 1:
            // 此时传入的 result 是 checkLogin() 的结果
            println("label 1")
            val isLogin = result;
            println(isLogin)
            label = 2;
            result = login(this); // this 是编译器添加的续体参数
            if (result == COROUTINE_SUSPENDED) {
                return COROUTINE_SUSPENDED;
            }
            break;
            case 2:
            // 此时传入的 result 是 login() 的结果
            println("label 2")
            val login = result;
            println(login)
            label = 3;
            result = getId(this); // this 是编译器添加的续体参数
            if (result == COROUTINE_SUSPENDED) {
                return COROUTINE_SUSPENDED;
            }
            break;
            case 3:
            // 此时传入的 result 是 getId() 的结果
            println("label 3")
            val id = result;
            println(id)
            return;
        }
    }
}*/

/*internal abstract class BaseContinuationImpl(
    public val completion: Continuation<Any?>?
) : Continuation<Any?>, CoroutineStackFrame, Serializable {
    public final override fun resumeWith(result: Result<Any?>) {
        var current = this
        var param = result
        while (true) {
            with(current) {
                val outcome: Result<Any?> = try {
                    // invokeSuspend() 执行续体下一个状态的逻辑
                    val outcome = invokeSuspend(param)
                    // 如果续体里调用到了挂起函数，则直接 return
                    if (outcome === COROUTINE_SUSPENDED) return
                    Result.success(outcome)
                } catch (exception: Throwable) {
                    Result.failure(exception)
                }
                if (completion is BaseContinuationImpl) {
                    current = completion
                    param = outcome
                } else {
                    // top-level completion reached -- invoke and return
                    // 对于 launch 启动的协程体，传入的 completion 是 AbstractCoroutine 子类对象
                    completion.resumeWith(outcome)
                    return
                }
            }
        }
    }
}*/
