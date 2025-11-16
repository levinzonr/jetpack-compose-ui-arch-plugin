package com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain

import com.levinzonr.arch.jetpackcompose.plugin.features.navigation.NavigationSettings
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.ollama.OllamaSettings
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.openai.OpenAISettings

data class Settings(
    val selectedAIClient: AIClientType,
    val ollama: OllamaSettings,
    val openai: OpenAISettings,
    val navigationSettings: NavigationSettings
) {
    companion object {
        val DEFAULT = Settings(
            selectedAIClient = AIClientType.Ollama,
            OllamaSettings(),
            OpenAISettings(""),
            NavigationSettings()
        )
    }
}