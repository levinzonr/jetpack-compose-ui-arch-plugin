package com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.openai


data class OpenAISettings(
    val apiKey: String,
    val timeoutSeconds: Int = 30,
    val modelId: String ="gpt-3.5-turbo",
    val host: String = "https://api.openai.com/v1/"
)