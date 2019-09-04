package com.casual.mysample.projectinterface

import com.casual.baseframe.http.BaseHttp
import com.casual.baseframe.base.BLModel
import com.casual.mysample.base.BaseService

abstract class ProjectViewModel : BLModel() {
    protected val service: BaseService by lazy {
        BaseHttp.getService<BaseService>()
    }
}