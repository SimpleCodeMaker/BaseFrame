package com.casual.baseframe.utils

import android.app.Activity
import kotlin.reflect.KClass


object ActivityStackManager {
    //直接获取app 整个activity栈，避免activity的各种启动模式
    fun finishActivity(vararg without: KClass<out Activity>? = arrayOf()) {
        try {
            //ActivityThread里持有了一个Map，这个Map的Value是ActivityClientRecord
            val activityThread = Class.forName("android.app.ActivityThread")
            val currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread")
            currentActivityThread.isAccessible = true
            //获取主线程对象
            val activityThreadObject = currentActivityThread.invoke(null)
            val mActivitiesField = activityThread.getDeclaredField("mActivities")
            mActivitiesField.isAccessible = true
            //ActivityClientRecord是持有一个Activity对象的，然后相当于是一个Activity被一个Map持有
            //源码ArrayMap<IBinder, ActivityClientRecord> mActivities = new ArrayMap<>();
            val mActivities = mActivitiesField.get(activityThreadObject) as Map<Any, Any>
            for ((_, value) in mActivities) {
                val activityClientRecordClass = value.javaClass
                val activityField = activityClientRecordClass.getDeclaredField("activity")
                activityField.isAccessible = true
                val activity = activityField.get(value) as Activity
                //如果包含就跳过
                if (without.contains(activity::class)) {
                    continue
                }
                activity.finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}