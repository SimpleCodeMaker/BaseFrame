package com.casual.mysample

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.casual.baseframe.base.BLActivity
import com.casual.mysample.present.SecondViewModel
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BLActivity<SecondViewModel>() {
    override val layoutId: Int = R.layout.activity_second

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.refreshLayout2 =refresh
        viewModel.list?.observe(this, Observer {data->
            refresh?.isRefreshState?.let {
                if (it){
                    viewModel.adapter.setNewData(data)
                    refresh?.pageIndex=1
                }else{
                    viewModel.adapter.addData(data)
                }
            }
        })
        viewModel.adapter.apply {bindToRecyclerView(recycle)}
//        commit.setOnClickListener {
//            viewModel.geta(2)
//        }
        refresh.refreshLoadMore {
            loadMore={
                viewModel.geta(it)
            }
            refresh= {
                viewModel.geta(1)
            }
        }
//        refresh.autoRefresh()
    }

    override val viewModel: SecondViewModel by lazy {
        ViewModelProviders.of(this).get(SecondViewModel::class.java)
    }
}