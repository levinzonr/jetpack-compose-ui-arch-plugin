package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain

import com.levinzonr.arch.jetpackcompose.plugin.core.loadStringResource
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

    object MockGenerator : AIGenerator {
        override suspend fun generate(ruleset: String, userPrompt: String): AIResponse {
            delay(5.seconds)
            return AIResponse(loadStringResource("ai_response_example.json")!!)
        }

        override suspend fun ping(): Boolean {
            return true
        }

    }

    suspend fun generate(name: String, userPrompt: String): Result<FeatureBreakdown> {
        return runCatching {
            val richUserPrompt = prompt(name, userPrompt)
            val example = loadStringResource("ai_response_example.json")!!
            val response = generator.generate(prompt_core(example), richUserPrompt)
            println(response.response)
            json.decodeFromString(FeatureBreakdown.serializer(), response.response)
        }
    }

    companion object {

        fun prompt_core(json: String) = "" +
                "Your job is to generated JSON file that would look like in this example: $json." +
                "Only respond with the JSON content. Nothing else. RAW JSON String, Not markdown: Omit ```json ``` thing " +
                ""

        fun prompt(name: String, description: String) = """ 
                Generate a JSON string that will act as feature breakdown for a feature called $name" 
                Based on the feature name and other input and requirements provided bellow, come up with properties that would describe the UI state of the feature 
                The properties should have a type that correspond with Kotlin types. For example: String, Int, List<String> etc." 
                The properties should have a default type, unless specified otherwise it should be the most simple values" 
                For example false for Boolean, emptyList() for list, empty string for String and etc " 
                The default values should always!! be as String JSON type, i.e "false" for Boolean, "" for String, \"emptyList()\" for List<String> etc" 
                The JSON should also contain a list of actions that the feature will have." 
                The naming convention for the actions should be following:" 
                   - In case its a button click or anything that prompts other action down the line: on[some]ButtonClick, replace [some] with the button name" 
                   - In case its text field or any component where user types something: on[some]Change, replace [some] with name of the field or component " 
                Some actions might contain some parameters, i.e String if its a text field change, or Item if its an item click. And each param must have a name." 
                 If possible this name should correspond with the name you would choose for the state parameters. " 
                I.e in case there is a username inside state and actions that changes that username, the name should username" 
                 Provide empty array of parameters in case its a simple click interaction on a button" 
                Actions should also have a type which is a string value and can be one of the following:" 
                - domain - domain type means that this action should be handled by the external component or system such as database or an API service (examples: login)" 
                - state_change - this type of action only affects the state of the feature, examples: password value change in text field" 
                - other - this type of action is for any other type of action that does not fit into the above categories such as navigation" 
                 Next each action should have an imperativeName form, it will be used in ViewModels, such as: changePassword or login" 

                "The name of the feature is $name and requirements from user that must be taken into account is following:"
                $description

        """
    }

}