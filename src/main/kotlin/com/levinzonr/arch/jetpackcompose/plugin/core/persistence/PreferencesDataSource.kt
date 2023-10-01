package com.levinzonr.arch.jetpackcompose.plugin.core.persistence


interface PreferencesDataSource {
    fun put(key: String, value: Boolean)
    fun get(key: String, default: Boolean) : Boolean

    fun put(key: String, value: String)
    fun get(key: String, default: String) : String
}

