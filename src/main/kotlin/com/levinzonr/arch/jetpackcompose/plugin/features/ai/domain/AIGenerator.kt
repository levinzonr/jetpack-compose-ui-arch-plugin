package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain

import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.AIResponse

interface AIGenerator {
    suspend fun generate(ruleset: String, userPrompt: String): AIResponse
    suspend fun ping(): Boolean
}