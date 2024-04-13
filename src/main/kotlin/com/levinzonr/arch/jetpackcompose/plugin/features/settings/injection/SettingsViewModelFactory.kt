package com.levinzonr.arch.jetpackcompose.plugin.features.settings.injection

import com.levinzonr.arch.jetpackcompose.plugin.dependencies.PluginDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.SettingsViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.data.SettingsRepositoryImpl

object SettingsViewModelFactory {

    fun create() : SettingsViewModel {
        return SettingsViewModel(
            settingsRepository = SettingsRepositoryImpl(PluginDependencies.preferences)
        )
    }
}