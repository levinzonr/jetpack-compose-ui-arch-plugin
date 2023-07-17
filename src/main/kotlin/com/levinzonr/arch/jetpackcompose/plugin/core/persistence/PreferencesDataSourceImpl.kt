package com.levinzonr.arch.jetpackcompose.plugin.core.persistence

import com.intellij.ide.util.PropertiesComponent

class PreferencesDataSourceImpl(
    private val component: PropertiesComponent
) : PreferencesDataSource {
    override fun put(key: String, value: Boolean) {
        component.setValue(key.projectKey, value)
    }

    override fun get(key: String, default: Boolean): Boolean {
        return component.getBoolean(key.projectKey)
    }

    private val String.projectKey: String get() {
        return "com.levinzonr.arch.jetpackcompose.plugin_$this"
    }
}