package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class StateProperties(
    val name: String,
    val type: String,
    val defaultValue: String
)