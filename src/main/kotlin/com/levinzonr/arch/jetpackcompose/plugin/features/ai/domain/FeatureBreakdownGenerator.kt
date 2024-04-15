package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain

import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.AIResponse
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.FeatureBreakdown
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

class FeatureBreakdownGenerator(
    private val generator: AIGenerator,
    private val json: Json = Dependencies.json
) {

    object MockGenerator: AIGenerator {
        override suspend fun generate(ruleset: String, userPrompt: String): AIResponse {
            delay(5.seconds)
            return AIResponse(JSON_EXAMPLE)
        }

        override suspend fun ping(): Boolean {
            return true
        }
    }

    suspend fun generate(name: String, userPrompt: String): Result<FeatureBreakdown> {
        return runCatching {
            val richUserPrompt = prompt(name, userPrompt)
            val response = generator.generate(PROMPT_CORE, richUserPrompt)
            json.decodeFromString(FeatureBreakdown.serializer(), response.response)
        }
    }

    companion object {

        const val JSON_EXAMPLE = "" +
                "{\n" +
                "  \"properties\": [\n" +
                "    {\n" +
                "      \"name\": \"username\",\n" +
                "      \"type\": \"String\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"password\",\n" +
                "      \"type\": \"String\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"items\",\n" +
                "      \"type\": \"List<String>\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"actions\": [\n" +
                "    {\n" +
                "      \"name\": \"onLoginClick\"\n" +
                "      \"params\": []" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"onForgotPasswordClick\"\n" +
                "      \"params\": []" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"onUsernameChange\"\n" +
                "      \"params\": [\"String\"]" +
                "    }\n" +
                "  ]\n" +
                "}"

        const val PROMPT_CORE = "" +
                "Your job is to generated JSON file that would look like in this example: $JSON_EXAMPLE." +
                "Only respond with the JSON content. Nothing else. RAW JSON String, Not markdown: Omit ```json ``` thing " +
                ""

        fun prompt(name: String, description: String) = "" +
                "Generate a JSON string that will act as feature breakdown for a feature called $name" +
                "Based on the feature name and other input and requirements provided bellow, come up with properties that would describe the UI state of the feature" +
                "The properties should have a type that correspond with Kotlin types. For example: String, Int, List<String> etc." +
                "The JSON should also contain a list of actions that the feature will have." +
                "The naming convention for the actions should be following:" +
                "\n- In case its a button click or anything that prompts other action down the line: on[some]ButtonClick, replace [some] with the button name" +
                "\n- In case its text field or any component where user types something: on[some]Change, replace [some] with name of the field or component " +
                "\nSome actions might contain some parameters, i.e String if its a text field change, or Item if its an item click." +
                " Provide empty array of parameters in case its a simple click interaction on a button" +

                "The name of the feature is $name and requirements from user that must be taken into account is follwoing:" +
                description

    }

}