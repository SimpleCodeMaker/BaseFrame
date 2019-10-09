package com.casual.module_database

import android.Manifest
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.casual.baseframe.base.BFActivity
import com.casual.baseframe.utils.ToastUtils
import com.casual.module_database.vm.RoomVM
import com.example.projectcode.RouteUris.Activitys.MODULE_DATABASE_MAIN
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_room.*

@Route(path = MODULE_DATABASE_MAIN)
class RoomActivity : BFActivity<RoomVM>() {
    val adapter = RoomDataAdapter()
    override val layoutId: Int = R.layout.activity_room
    override fun initView(savedInstanceState: Bundle?) {
        adddata.setOnClickListener {
            RxPermissions(this).request(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).subscribe {
                if (it) {
                    viewModel.insertData()
                } else {
                    ToastUtils.showShort("没权限")
                }
            }
        }
        getdata.setOnClickListener {
            viewModel.getData()
        }
        deletedata.setOnClickListener {
            viewModel.deleteData()
        }
        adapter.bindToRecyclerView(room_recycle)

    }

    override val viewModel: RoomVM by lazy {
        ViewModelProviders.of(this).get(RoomVM::class.java)
    }

}