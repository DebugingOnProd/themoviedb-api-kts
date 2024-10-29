package org.lhq.entity.movie


import java.util.*

// 定义运行时变更的数据类
data class RuntimeChange(
    val id: String,
    val action: String,
    val time: Date,
    val iso6391: String,
    val iso31661: String,
    val value: Int,
    val originalValue: Int
)

// 定义视频变更的数据类
data class Video(
    val id: String,
    val name: String,
    val key: String,
    val size: Int,
    val site: String,
    val type: String
)

data class VideoChange(
    val id: String,
    val action: String,
    val time: Date,
    val iso6391: String,
    val iso31661: String,
    val value: Video,
    val originalValue: Video
)

// 定义上映日期变更的数据类
data class ReleaseDate(
    val certification: String,
    val descriptors: List<String>,
    val iso31661: String,
    val iso6391: String,
    val note: String,
    val releaseDate: Date,
    val type: Int
)

data class ReleaseDateChange(
    val id: String,
    val action: String,
    val time: Date,
    val iso6391: String,
    val iso31661: String,
    val value: ReleaseDate,
    val originalValue: ReleaseDate?
)

// 定义总的变更数据类
data class ChangeItem<T>(
    val key: String,
    val items: List<T>
)
