package com.levinzonr.arch.jetpackcompose.plugin.features.ai.injection

import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.PluginDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.ollama.OllamaGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.openai.OpenAiGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.AIGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.AIClientType

object AIGeneratorFactory {

    fun create(): AIGenerator {
        val settings = PluginDependencies.settings.get()
        return when (settings.selectedAIClient) {
            AIClientType.Ollama -> OllamaGenerator(
                dispatcher = Dependencies.ioDispatcher,
                settings = settings.ollama
            )

            AIClientType.OpenAI -> OpenAiGenerator(
                dispatcher = Dependencies.ioDispatcher,
                settings = settings.openai
            )
        }
    }
}