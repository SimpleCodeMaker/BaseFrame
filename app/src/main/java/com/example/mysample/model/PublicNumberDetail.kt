package com.example.mysample.model

import com.example.baselibrary.base.BLBean


data class PublicNumberDetail(
    val curPage: Int?, // 2
    val datas: List<DataX?>?,
    val offset: Int?, // 20
    val over: Boolean?, // false
    val pageCount: Int?, // 40
    val size: Int?, // 20
    val total: Int? // 782
):BLBean()

data class DataX(
    val apkLink: String?,
    val author: String?, // 鸿洋
    val chapterId: Int?, // 408
    val chapterName: String?, // 鸿洋
    val collect: Boolean?, // false
    val courseId: Int?, // 13
    val desc: String?,
    val envelopePic: String?,
    val fresh: Boolean?, // false
    val id: Int?, // 8745
    val link: String?, // https://mp.weixin.qq.com/s/B3hZtdyXtpecW0Qnq2YBOg
    val niceDate: String?, // 2019-06-21
    val origin: String?,
    val prefix: String?,
    val projectLink: String?,
    val publishTime: Long?, // 1561046400000
    val superChapterId: Int?, // 408
    val superChapterName: String?, // 公众号
    val tags: List<Tag?>?,
    val title: String?, // 今天想感谢一个伙伴
    val type: Int?, // 0
    val userId: Int?, // -1
    val visible: Int?, // 1
    val zan: Int? // 0
):BLBean()

data class Tag(
    val name: String?, // 公众号
    val url: String? // /wxarticle/list/408/1
)