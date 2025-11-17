package com.levinzonr.arch.jetpackcompose.plugin.features.settings

import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.core.ObservableValue
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.ollama.OllamaGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.openai.OpenAiGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.AIClientType
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.SettingsRepository
import kotlinx.coroutines.launch

class AISettingsViewModel(
    private val settingsRepository: SettingsRepository
) : BaseViewModel() {

    private val settings = settingsRepository.get()
    private var newSettings = settings

    var clientType: AIClientType
        get() = newSettings.selectedAIClient
        set(value) {
            newSettings = newSettings.copy(selectedAIClient = value)
        }


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

    var timeoutSeconds: Int
        get() = newSettings.ollama.timeoutSeconds.toInt()
        set(value) {
            newSettings = newSettings.copy(ollama = newSettings.ollama.copy(timeoutSeconds = value.toLong()))
        }

    // OpenAI Settings
    var openaiApiKey: String
        get() = newSettings.openai.apiKey
        set(value) {
            newSettings = newSettings.copy(openai = newSettings.openai.copy(apiKey = value))
        }

    var openaiHost: String
        get() = newSettings.openai.host
        set(value) {
            newSettings = newSettings.copy(openai = newSettings.openai.copy(host = value))
        }

    var openaiModelId: String
        get() = newSettings.openai.modelId
        set(value) {
            newSettings = newSettings.copy(openai = newSettings.openai.copy(modelId = value))
        }

    var openaiTimeoutSeconds: Int
        get() = newSettings.openai.timeoutSeconds
        set(value) {
            newSettings = newSettings.copy(openai = newSettings.openai.copy(timeoutSeconds = value))
        }

    var openaiConnectionStatus = ObservableValue("")

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

    fun testOpenAIConnection() {
        openaiConnectionStatus.set("Testing...")
        scope.launch {
            try {
                val openai = OpenAiGenerator(newSettings.openai, Dependencies.ioDispatcher)
                val success = openai.ping()
                val result = if (success) "Success" else "Failed"
                openaiConnectionStatus.set(result)
            } catch (e: Exception) {
                openaiConnectionStatus.set(e.message ?: e.toString())
            }
        }
    }
}
