package com.example.baselibrary.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/09/29
 * desc  : 吐司相关工具类
</pre> *
 */
class ToastUtils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        private var sToast: Toast? = null
        private var gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
        private var xOffset = 0
        private var yOffset = (64 * Utils.getContext().resources.displayMetrics.density + 0.5).toInt()
        @SuppressLint("StaticFieldLeak")
        private var customView: View? = null
        private val sHandler = Handler(Looper.getMainLooper())

        /**
         * 设置吐司位置
         *
         * @param gravity 位置
         * @param xOffset x偏移
         * @param yOffset y偏移
         */
        fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
            ToastUtils.gravity = gravity
            ToastUtils.xOffset = xOffset
            ToastUtils.yOffset = yOffset
        }

        /**
         * 设置吐司view
         *
         * @param layoutId 视图
         */
        fun setView(@LayoutRes layoutId: Int) {
            val inflate = Utils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            ToastUtils.customView = inflate.inflate(layoutId, null)
        }

        /**
         * 设置吐司view
         *
         * @param view 视图
         */
        fun setView(view: View) {
            ToastUtils.customView = view
        }

        /**
         * 获取吐司view
         *
         * @return view 自定义view
         */
        val view: View?
            get() {
                if (customView != null) return customView
                return if (sToast != null) sToast!!.view else null
            }

        /**
         * 安全地显示短时吐司
         *
         * @param text 文本
         */
        fun showShortSafe(text: CharSequence) {
            sHandler.post { showIt(text, Toast.LENGTH_SHORT) }
        }

        /**
         * 安全地显示短时吐司
         *
         * @param resId 资源Id
         */
        fun showShortSafe(@StringRes resId: Int) {
            sHandler.post { show(resId, Toast.LENGTH_SHORT) }
        }

        /**
         * 安全地显示短时吐司
         *
         * @param resId 资源Id
         * @param args  参数
         */
        fun showShortSafe(@StringRes resId: Int, vararg args: Any) {
            sHandler.post { show(resId, Toast.LENGTH_SHORT, *args) }
        }

        /**
         * 安全地显示短时吐司
         *
         * @param format 格式
         * @param args   参数
         */
        fun showShortSafe(format: String, vararg args: Any) {
            sHandler.post { show(format, Toast.LENGTH_SHORT, *args) }
        }

        /**
         * 安全地显示长时吐司
         *
         * @param text 文本
         */
        fun showLongSafe(text: CharSequence) {
            sHandler.post { showIt(text, Toast.LENGTH_LONG) }
        }

        /**
         * 安全地显示长时吐司
         *
         * @param resId 资源Id
         */
        fun showLongSafe(@StringRes resId: Int) {
            sHandler.post { show(resId, Toast.LENGTH_LONG) }
        }

        /**
         * 安全地显示长时吐司
         *
         * @param resId 资源Id
         * @param args  参数
         */
        fun showLongSafe(@StringRes resId: Int, vararg args: Any) {
            sHandler.post { show(resId, Toast.LENGTH_LONG, *args) }
        }

        /**
         * 安全地显示长时吐司
         *
         * @param format 格式
         * @param args   参数
         */
        fun showLongSafe(format: String, vararg args: Any) {
            sHandler.post { show(format, Toast.LENGTH_LONG, *args) }
        }

        /**
         * 显示短时吐司
         *
         * @param text 文本
         */
        fun showShort(text: CharSequence) {
            showIt(text, Toast.LENGTH_SHORT)
        }

        /**
         * 显示短时吐司
         *
         * @param resId 资源Id
         */
        fun showShort(@StringRes resId: Int) {
            show(resId, Toast.LENGTH_SHORT)
        }

        /**
         * 显示短时吐司
         *
         * @param resId 资源Id
         * @param args  参数
         */
        fun showShort(@StringRes resId: Int, vararg args: Any) {
            show(resId, Toast.LENGTH_SHORT, *args)
        }

        /**
         * 显示短时吐司
         *
         * @param format 格式
         * @param args   参数
         */
        fun showShort(format: String, vararg args: Any) {
            if (format.isEmpty() && args.isEmpty()) return
            show(format, Toast.LENGTH_SHORT, *args)
        }

        /**
         * 显示长时吐司
         *
         * @param text 文本
         */
        fun showLong(text: CharSequence) {
            showIt(text, Toast.LENGTH_LONG)
        }

        /**
         * 显示长时吐司
         *
         * @param resId 资源Id
         */
        fun showLong(@StringRes resId: Int) {
            show(resId, Toast.LENGTH_LONG)
        }

        /**
         * 显示长时吐司
         *
         * @param resId 资源Id
         * @param args  参数
         */
        fun showLong(@StringRes resId: Int, vararg args: Any) {
            show(resId, Toast.LENGTH_LONG, *args)
        }

        /**
         * 显示长时吐司
         *
         * @param format 格式
         * @param args   参数
         */
        fun showLong(format: String, vararg args: Any) {
            show(format, Toast.LENGTH_LONG, *args)
        }


        fun showDuration(format: String, duration: Int, vararg args: Any) {
            show(format, duration, *args)
        }

        /**
         * 显示吐司
         *
         * @param resId    资源Id
         * @param duration 显示时长
         */
        private fun show(@StringRes resId: Int, duration: Int) {
            show(Utils.getContext().resources.getText(resId).toString(), duration)
        }

        /**
         * 显示吐司
         *
         * @param resId    资源Id
         * @param duration 显示时长
         * @param args     参数
         */
        private fun show(@StringRes resId: Int, duration: Int, vararg args: Any) {
            show(String.format(Utils.getContext().resources.getString(resId), *args), duration)
        }

        /**
         * 显示吐司
         *
         * @param format   格式
         * @param duration 显示时长
         * @param args     参数
         */
        private fun show(format: String, duration: Int, vararg args: Any) {
            showIt(String.format(format, *args), duration)
        }

        /**
         * 显示吐司
         *
         * @param text     文本
         * @param duration 显示时长
         */
        @SuppressLint("ShowToast")
        private fun showIt(text: CharSequence, duration: Int) {
            cancel()
            if (customView != null) {
                sToast = Toast(Utils.getContext())
                sToast!!.view = customView
                sToast!!.duration = duration
                sToast!!.setText(text)
            } else {
                sToast = Toast.makeText(Utils.getContext(), text, duration)
            }
            sToast!!.setGravity(gravity, xOffset, yOffset)
            sToast!!.show()
        }

        /**
         * 取消吐司显示
         */
        fun cancel() {
            if (sToast != null) {
                sToast!!.cancel()
                sToast = null
            }
        }
    }
}