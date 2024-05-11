package com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain

import com.levinzonr.arch.jetpackcompose.plugin.features.ollama.OllamaSettings

data class Settings(
    val ollama: OllamaSettings
) {
    companion object {
        val DEFAULT = Settings(OllamaSettings())
    }
}