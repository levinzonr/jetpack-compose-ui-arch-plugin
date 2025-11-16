package com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.openai

import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.AIGenerator
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.AIResponse
import com.openai.client.okhttp.OpenAIOkHttpClient
import com.openai.models.chat.completions.ChatCompletionCreateParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.time.Duration


class OpenAiGenerator(
    private val settings: OpenAISettings,
    private val dispatcher: CoroutineDispatcher
) : AIGenerator {


    private val client = OpenAIOkHttpClient.builder()
        .apiKey(settings.apiKey)
        .baseUrl(settings.host)
        .timeout(Duration.ofSeconds(settings.timeoutSeconds.toLong()))
        .build()


    override suspend fun generate(ruleset: String, userPrompt: String): AIResponse {
        return withContext(dispatcher) {
            val params = ChatCompletionCreateParams.builder()
                .addSystemMessage(ruleset)
                .addUserMessage(userPrompt)
                .model(settings.modelId)
                .build()

            val response = client.chat().completions().create(params)
            AIResponse(response.choices().first().message().content().orElseThrow())
        }

    }

    override suspend fun ping(): Boolean {
        return withContext(dispatcher) {
            val params = ChatCompletionCreateParams.builder()
                .addUserMessage("Say this is a test")
                .model(settings.modelId)
                .build()

            client.chat().completions().create(params)
            true
        }

    }
}