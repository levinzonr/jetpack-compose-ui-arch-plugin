package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain

import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.CodeSnippet

interface AISnippetGenerator {
    suspend fun generate(prompt: String): CodeSnippet
    suspend fun ping(): Boolean
}