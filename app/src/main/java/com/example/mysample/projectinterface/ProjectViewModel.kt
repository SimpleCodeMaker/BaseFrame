package com.example.mysample.projectinterface

import com.example.baselibrary.http.BaseHttp
import com.example.baselibrary.base.BLModel
import com.example.mysample.base.BaseService

abstract class ProjectViewModel : BLModel() {
    protected val service: BaseService by lazy {
        BaseHttp.getService<BaseService>()
    }
}