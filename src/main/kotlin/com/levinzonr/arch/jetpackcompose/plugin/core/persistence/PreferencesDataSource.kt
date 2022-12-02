package com.levinzonr.arch.jetpackcompose.plugin.core.persistence


interface PreferencesDataSource {
    fun put(key: String, value: Boolean)
    fun get(key: String) : Boolean
}

