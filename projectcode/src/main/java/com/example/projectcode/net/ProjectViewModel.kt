package com.example.projectcode.net

import com.casual.baseframe.http.BaseHttp
import com.casual.baseframe.base.BFModel

abstract class ProjectViewModel : BFModel() {
    protected val service: Api by lazy {
        BaseHttp.getService<Api>()
    }
}