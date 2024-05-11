package com.levinzonr.arch.jetpackcompose.plugin.features.ollama

data class OllamaSettings(
    val host: String = "http://localhost:11434/",
    val model: String = "llama2",
    val timeoutSeconds: Long = 35
)