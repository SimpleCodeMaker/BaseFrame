package com.example.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BLFragment<T : ViewModel> : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView(savedInstanceState)
        super.onActivityCreated(savedInstanceState)
    }

    abstract val layoutId: Int
    abstract fun initView(savedInstanceState: Bundle?)
    abstract val viewModel: T
    fun <V : View> find(id: Int): V? {
        return view?.findViewById(id)
    }
}