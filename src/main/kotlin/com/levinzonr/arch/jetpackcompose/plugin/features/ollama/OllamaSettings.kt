package com.levinzonr.arch.jetpackcompose.plugin.features.ollama

data class OllamaSettings(
    val host: String = "http://localhost:11434/",
    val model: String = "codegemma",
    val timeoutSeconds: Long = 35
)