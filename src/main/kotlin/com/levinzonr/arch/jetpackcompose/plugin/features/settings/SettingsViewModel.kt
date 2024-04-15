package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
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
        val ollama = OllamaGenerator(newSettings.ollama)
        scope.launch {
            try {
                val result = ollama.ping()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}