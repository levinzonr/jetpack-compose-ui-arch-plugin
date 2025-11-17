package com.levinzonr.arch.jetpackcompose.plugin.features.settings.injection

import com.levinzonr.arch.jetpackcompose.plugin.dependencies.PluginDependencies
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.AISettingsViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.UISettingsViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.data.SettingsRepositoryImpl

object SettingsViewModelFactory {

    fun createAI() : AISettingsViewModel {
        return AISettingsViewModel(
            settingsRepository = PluginDependencies.settings
        )
    }

    fun createUI() : UISettingsViewModel {
        return UISettingsViewModel(
            settingsRepository = PluginDependencies.settings
        )
    }
}