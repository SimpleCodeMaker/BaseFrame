package com.example.projectcode

import android.app.Activity
import android.app.Application
import android.os.Bundle
import kotlin.reflect.KClass

object ActivitysManager : Application.ActivityLifecycleCallbacks {
    private val activityList = LinkedHashMap<KClass<out Activity>, Activity>()
    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStarted(p0: Activity) {
    }

    override fun onActivityDestroyed(p0: Activity) {
        activityList.remove(p0::class)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        activityList.put(p0::class,p0)
    }

    override fun onActivityResumed(p0: Activity) {
    }

    /**
     * 如果不想finish可以添加对应
     * activity的class
     */
    fun finishActivity(vararg without: KClass<out Activity>? = arrayOf()){
        for (entry in activityList) {
            if (without.contains(entry.key)){
                return
            }
            entry.value.finish()
        }
    }

    /**
     * 完全退出
     */
    fun exit(){
        finishActivity()
        System.exit(0);
    }
}