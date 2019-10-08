package com.casual.baseframe.utils

import android.app.Activity
import java.lang.reflect.AccessibleObject.setAccessible



object ActivityStackManager {
    //目前先随便写个list
    val activityList = ArrayList<Activity>()

    fun getAllActivitys(): List<Activity> {
        val list = ArrayList<Activity>()
        try {
            val activityThread = Class.forName("android.app.ActivityThread")
            val currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread")
            currentActivityThread.isAccessible = true
            //获取主线程对象
            val activityThreadObject = currentActivityThread.invoke(null)
            val mActivitiesField = activityThread.getDeclaredField("mActivities")
            mActivitiesField.isAccessible = true
            val mActivities = mActivitiesField.get(activityThreadObject) as Map<Any, Any>
            for ((_, value) in mActivities) {
                val activityClientRecordClass = value.javaClass
                val activityField = activityClientRecordClass.getDeclaredField("activity")
                activityField.isAccessible = true
                val o = activityField.get(value)
                list.add(o as Activity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return list
    }
}