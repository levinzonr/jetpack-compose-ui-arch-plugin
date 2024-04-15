package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain

import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.FeatureBreakdown
import kotlinx.serialization.json.Json

class FeatureBreakdownRepository(
    private val generator: AIGenerator,
    private val json: Json = Dependencies.json
) {

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
                "      \"property\": \"username\",\n" +
                "      \"type\": \"String\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"property\": \"password\",\n" +
                "      \"type\": \"String\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"property\": \"items\",\n" +
                "      \"type\": \"List<String>\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"actions\": [\n" +
                "    {\n" +
                "      \"name\": \"onLoginClick\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"onForgotPasswordClick\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"onUsernameChange\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"
        const val JSON_ERROR = "" +
                "{ error: [Message] }"


        const val PROMPT_CORE = "" +
                "Your job is to generated JSON file that would look like in this example: $JSON_EXAMPLE." +
                "Only respond with the JSON content. Nothing else. RAW JSON: Omit ```json ``` thing " +
                ""

        fun prompt(name: String, description: String) = "" +
                "Generate a JSON string that will act as feature breakdown for a feature called $name" +
                "Based on the feature name and other input and requirements provided bellow, come up with properties that would describe the UI state of the feature" +
                "The properties should have a type that correspond with Kotlin types. For example: String, Int, List<String> etc." +
                "The JSON should also contain a list of actions that the feature will have." +
                "The naming convention for the actions should be following:" +
                "\n- In case its a button click or anything that prompts other action down the line: on[some]ButtonClick, replace [some] with the button name" +
                "\n- In case its text field or any component where user types something: on[some]Change, replace [some] with name of the field or component " +

                "The name of the feature is $name and requirements from user that must be taken into account is follwoing:" +
                description

    }

}