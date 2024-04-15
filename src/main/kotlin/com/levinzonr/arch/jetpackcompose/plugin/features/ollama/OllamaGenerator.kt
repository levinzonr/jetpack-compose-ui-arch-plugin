package com.levinzonr.arch.jetpackcompose.plugin.features.ollama

import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.AIGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.AIResponse
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.OllamaSettings
import io.github.amithkoujalgi.ollama4j.core.OllamaAPI
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder
import io.github.amithkoujalgi.ollama4j.core.utils.PromptBuilder
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class OllamaGenerator(
    private val settings: OllamaSettings,
    private val dispatcher: CoroutineContext = Dependencies.ioDispatcher,
) : AIGenerator {

    private val api = OllamaAPI(settings.host)

    init {
        api.setVerbose(true)
        api.setRequestTimeoutSeconds(20)
    }

    override suspend fun generate(ruleset: String, userPrompt: String): AIResponse {
        return withContext(dispatcher) {
            val prompt = PromptBuilder()
                .add(ruleset)
                .addSeparator()
                .addLine(userPrompt)
            println("Ollamruns : ${settings.model}")
            val response = api.generate(settings.model, prompt.build(), OptionsBuilder().build())
            // ollama refuses to return json without code blocks
            val trimmedResponse = response.response
                .replace("```json", "")
                .replace("```", "")
            AIResponse(trimmedResponse)
        }

    }

    override suspend fun ping(): Boolean {
        api.generate(settings.model, "Hello!", OptionsBuilder().build())
        return true
    }
}

