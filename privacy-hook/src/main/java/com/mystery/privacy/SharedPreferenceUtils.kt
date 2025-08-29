package com.mystery.privacy;

import android.content.Context
import android.content.SharedPreferences

internal object SharedPreferenceUtils {

    private const val PREF_NAME = "privacy_hook_cache"
    private const val SEPARATOR = "|||SEPARATOR|||" // 定义一个不太可能出现在数据中的分隔符

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    /**
     * 存储字符串List
     */
    fun saveStringList(context: Context, key: String, list: List<String>) {
        // 将List用分隔符拼接成单个字符串
        val joinedString = list.joinToString(separator = SEPARATOR)
        getPreferences(context).edit()
            .putString(key, joinedString)
            .apply()
    }

    /**
     * 读取字符串List
     */
    fun getStringList(context: Context, key: String): List<String> {
        val savedString = getPreferences(context).getString(key, null)
        return if (savedString.isNullOrEmpty()) {
            emptyList()
        } else {
            // 将字符串用分隔符拆分回List
            savedString.split(SEPARATOR)
        }
    }

    /**
     * 存储自定义对象List（需要对象实现toString()和parse()方法）
     */
    fun <T> saveObjectList(context: Context, key: String, list: List<T>, toString: (T) -> String) {
        val stringList = list.map { toString(it) }
        saveStringList(context, key, stringList)
    }

    /**
     * 读取自定义对象List
     */
    fun <T> getObjectList(context: Context, key: String, parse: (String) -> T): List<T> {
        return getStringList(context, key).map { parse(it) }
    }

    /**
     * 清除指定key的存储
     */
    fun clear(context: Context, key: String) {
        getPreferences(context).edit()
            .remove(key)
            .apply()
    }

}