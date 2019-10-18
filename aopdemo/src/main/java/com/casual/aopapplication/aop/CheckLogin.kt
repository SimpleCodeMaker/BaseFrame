package com.example.module_main.aop

@Retention(AnnotationRetention.RUNTIME)//存储在编译后的 Class 文件，反射可见。
@Target(AnnotationTarget.FUNCTION)//方法（不包括构造函数）
annotation class CheckLogin