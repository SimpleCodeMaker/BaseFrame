package com.example.module_main.activity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.casual.baseframe.base.BFActivity
import com.example.module_main.R
import com.example.module_main.vm.SecondViewModel
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BFActivity<SecondViewModel>() {
    var index = 0
    override val layoutId: Int = R.layout.activity_second
    override fun initView(savedInstanceState: Bundle?) {
        viewModel.refreshLayout2 =refresh
        viewModel.adapter.apply {bindToRecyclerView(recycle)}
        viewModel.list?.observe(this, Observer {data->
            viewModel.adapter.addData(data)
        })
        refresh.setOnRefreshListener {
            viewModel.adapter.data.clear()
            index=0
            getData(index)
        }

        refresh.setOnLoadMoreListener {
            getData(++index)
        }
    }

    override val viewModel: SecondViewModel by lazy {
        ViewModelProviders.of(this).get(SecondViewModel::class.java)
    }
    fun getData(index:Int){
viewModel.getData(index)
    }
}