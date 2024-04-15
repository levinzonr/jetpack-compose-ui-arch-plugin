package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.core.ObservableValue
import com.levinzonr.arch.jetpackcompose.plugin.features.ollama.OllamaGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
): BaseViewModel() {

    private val settings = settingsRepository.get()
    private var newSettings = settings

    var host: String
        get() = newSettings.ollama.host
        set(value) {
            newSettings = newSettings.copy(ollama = newSettings.ollama.copy(host = value))
        }

    var ollamaConnectionStatus = ObservableValue("")


    var model: String
        get() = newSettings.ollama.model
        set(value) {
            newSettings = newSettings.copy(ollama = newSettings.ollama.copy(model = value))
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
                val success =  ollama.ping()
                val result = if (success) "Success" else "Failed"
                ollamaConnectionStatus.set(result)
            } catch (e: Exception) {
                ollamaConnectionStatus.set(e.message ?: e.toString())
            }
        }
    }

}