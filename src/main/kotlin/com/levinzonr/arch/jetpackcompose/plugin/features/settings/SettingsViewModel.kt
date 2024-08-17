package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.core.ObservableValue
import com.levinzonr.arch.jetpackcompose.plugin.features.navigation.NavigationSettings
import com.levinzonr.arch.jetpackcompose.plugin.features.ollama.OllamaGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : BaseViewModel() {

    private val settings = settingsRepository.get()
    private var newSettings = settings

    var host: String
        get() = newSettings.ollama.host
        set(value) {
            newSettings = newSettings.copy(ollama = newSettings.ollama.copy(host = value))
        }

    var ollamaConnectionStatus = ObservableValue("")


    var navSuffix: String
        get() = newSettings.navigationSettings.classSuffix
        set(value) {
            newSettings =
                newSettings.copy(navigationSettings = newSettings.navigationSettings.copy(classSuffix = value))
        }

    var kiwiEnabled: Boolean
        get() = newSettings.navigationSettings.type == NavigationSettings.NavigationType.Kiwi
        set(value) {
            newSettings =
                newSettings.copy(navigationSettings = newSettings.navigationSettings.copy(type = if (value) NavigationSettings.NavigationType.Kiwi else NavigationSettings.NavigationType.ComposeDestinations))
        }

    var composeDestinationsEnabled: Boolean
        get() = newSettings.navigationSettings.type == NavigationSettings.NavigationType.ComposeDestinations
        set(value) {
            newSettings =
                newSettings.copy(navigationSettings = newSettings.navigationSettings.copy(type = if (value) NavigationSettings.NavigationType.ComposeDestinations else NavigationSettings.NavigationType.Kiwi))
        }

    var model: String
        get() = newSettings.ollama.model
        set(value) {
            newSettings = newSettings.copy(ollama = newSettings.ollama.copy(model = value))
        }

    var timeoutSeconds: Int
        get() = newSettings.ollama.timeoutSeconds.toInt()
        set(value) {
            newSettings = newSettings.copy(ollama = newSettings.ollama.copy(timeoutSeconds = value.toLong()))
        }

    fun reset() {
        newSettings = settings
    }

    fun apply() {
        settingsRepository.set(newSettings)
    }

    fun testOllamaConnection() {
        ollamaConnectionStatus.set("Testing...")
        scope.launch {
            try {
                val ollama = OllamaGenerator(newSettings.ollama)
                val success = ollama.ping()
                val result = if (success) "Success" else "Failed"
                ollamaConnectionStatus.set(result)
            } catch (e: Exception) {
                ollamaConnectionStatus.set(e.message ?: e.toString())
            }
        }
    }

}