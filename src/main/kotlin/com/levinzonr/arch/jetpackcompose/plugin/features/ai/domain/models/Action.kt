package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class  Action(
    val name: String,
    val params: List<Param>,
    val imperativeName: String,
    val type: Type
) {

    @Serializable
    data class Param(val name: String, val type: String)

    @Serializable(with = ActionTypeSerializer::class)
    enum class Type {
        Domain, StateChange, Other
    }

    val namedParams = params.joinToString(",") { "${it.name}: ${it.type}" }
    val typedParams = params.joinToString(",") { it.type }
}