package com.example.module_main.aop

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

@Aspect//标识切面
class CheckLoginAspect {

    private var isLogin = false

    //切入点
    @Pointcut("execution(@com.gfd.aop.CheckLogin * *(..))")
    fun checkLogin(){
    }

    @Around("checkLogin()")//环绕通知，先执行通知
    @Throws(Throwable::class)//可能抛出的异常
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint){
        Log.d("aroundJoinPoint","aroundJoinPoint")
        val methodSignature = joinPoint.signature as MethodSignature
        val checkLogin : CheckLogin? = methodSignature.method.getAnnotation(CheckLogin::class.java)
        if(checkLogin != null){
            val context = joinPoint.`this` as Context
            if(false){//如果已经登入再去执行对应的内容
                joinPoint.proceed()//执行标注的方法中的内容
            }else{
                Toast.makeText(context,"请先登入",Toast.LENGTH_SHORT).show()
            }
        }
    }
}