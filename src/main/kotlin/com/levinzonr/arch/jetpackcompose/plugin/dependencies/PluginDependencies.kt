package com.levinzonr.arch.jetpackcompose.plugin.dependencies

import com.intellij.ide.util.PropertiesComponent
import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSourceImpl
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.data.SettingsRepositoryImpl

object PluginDependencies {
    private val properties = PropertiesComponent.getInstance()
    val preferences = PreferencesDataSourceImpl(properties)
    val settings = SettingsRepositoryImpl(preferences)
}