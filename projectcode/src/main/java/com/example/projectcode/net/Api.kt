package com.example.projectcode.net

import com.example.projectcode.bean.PublicNumberBean
import com.example.projectcode.bean.PublicNumberDetail
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/chapters/json")
    fun getPublicNumberList(): Deferred<ProjectData<List<PublicNumberBean>>>

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getPublicNumberList2(): ProjectData<List<PublicNumberBean>>

    /**
     * 查看某个公众号历史数据
     */
    @GET("/wxarticle/list/{ID}/{pageindex}/json")
    suspend fun getPublicNumberDetailList(@Path("ID") ID: Int, @Path("pageindex") pageindex: Int): ProjectData<PublicNumberDetail>
}


