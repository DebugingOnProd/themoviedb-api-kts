package org.lhq.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Collections

object GsonUtils {

    private val gson : Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    private val logger = LoggerFactory.getLogger(GsonUtils::class.java)

    /**
     * 将对象转换为json字符串
     */
    fun <T> toJson(obj: T) : String{
        return gson.toJson(obj)
    }

    fun getGson() : Gson{
        return gson
    }
    fun getLogger() : Logger {
        return logger
    }

    inline fun <reified T> fromJson(json:String?) : T? {
        if (json.isNullOrEmpty()) return null
        try {
            return getGson().fromJson(json, T::class.java)
        } catch (e: Exception) {
            getLogger().error("json字符串转换为对象异常：${e.message}")
            return null
        }
    }

    /**
     * json字符串转换为对象集合
     */

    inline fun <reified T> fromJsonList(json: String?): List<T> ? {
        try {
            return getGson().fromJson(json, object : TypeToken<List<T>>() {}.type)
        } catch (e: Exception) {
            getLogger().error("json字符串转换为对象集合异常：${e.message}")
            return Collections.emptyList()
        }
    }

    inline fun <reified K,reified V> fromJsonMap(json:String): Map<K,V>?{
        try {
            return getGson().fromJson(json,object :TypeToken<Map<K,V>>(){}.type)
        } catch (e: Exception) {
            getLogger().error("json字符串转换为对象Map异常：${e.message}")
            return null
        }
    }

}