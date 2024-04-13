package com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain

data class OllamaSettings(
    val host: String = "http://localhost:11434/",
    val model: String = "llama2",
)