package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class StateProperties(
    val name: String,
    val type: String,
    @SerialName("defaultValue")
    val defaultValueJson: JsonElement
) {
    val defaultValue get() =  defaultValueJson.toString()
}