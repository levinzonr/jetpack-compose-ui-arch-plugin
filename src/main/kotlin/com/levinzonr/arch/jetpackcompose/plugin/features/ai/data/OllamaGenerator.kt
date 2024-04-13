package com.levinzonr.arch.jetpackcompose.plugin.features.ai.data

import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.AISnippetGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.CodeSnippet
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.OllamaSettings
import io.github.amithkoujalgi.ollama4j.core.OllamaAPI
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder

class OllamaGenerator(
    private val settings: OllamaSettings
) : AISnippetGenerator {

    private val api = OllamaAPI(settings.host)
    init { api.setVerbose(true) }

    override suspend fun generate(prompt: String): CodeSnippet {
        val response = api.generate(settings.model, prompt, OptionsBuilder().build())
        return CodeSnippet(response.response)
    }

    override suspend fun ping(): Boolean {
        return api.ping()
    }
}