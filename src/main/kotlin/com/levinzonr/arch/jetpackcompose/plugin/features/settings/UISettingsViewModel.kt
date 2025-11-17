package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.livetemplates.LiveTemplateManager
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.SettingsRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.ui.UILibraryType

class UISettingsViewModel(
    private val settingsRepository: SettingsRepository
) : BaseViewModel() {

    private val settings = settingsRepository.get()
    private var newSettings = settings

    var uiLibraryType: UILibraryType
        get() = newSettings.uiLibrarySettings.type
        set(value) {
            newSettings = newSettings.copy(uiLibrarySettings = newSettings.uiLibrarySettings.copy(type = value))
        }

    fun reset() {
        newSettings = settings
        // Update live templates when resetting to current settings
        LiveTemplateManager.updateTemplatesForUILibrary(settings.uiLibrarySettings.type)
    }

    fun apply() {
        settingsRepository.set(newSettings)
        // Update live templates based on UI Library setting
        LiveTemplateManager.updateTemplatesForUILibrary(newSettings.uiLibrarySettings.type)
    }
}
