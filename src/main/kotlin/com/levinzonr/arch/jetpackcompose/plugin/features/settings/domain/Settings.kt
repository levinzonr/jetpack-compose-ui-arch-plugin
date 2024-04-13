package com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain

data class Settings(
    val ollama: OllamaSettings
) {
    companion object {
        val DEFAULT = Settings(OllamaSettings())
    }
}