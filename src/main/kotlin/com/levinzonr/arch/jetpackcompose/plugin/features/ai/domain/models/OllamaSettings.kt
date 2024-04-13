package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

data class OllamaSettings(
    val host: String = "http://localhost:11434/",
    val model: String = "llama2",
)