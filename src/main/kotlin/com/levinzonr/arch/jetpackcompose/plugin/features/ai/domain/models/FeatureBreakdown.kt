package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class FeatureBreakdown(
    val properties: List<StateProperties>,
    val actions: List<Action>
) {

    val propertyStatements = properties.joinToString("\n") {
        "val ${it.name}: ${it.type},"
    }

    val actionStatements= actions.joinToString("\n") {
        val params = it.params.joinToString(",")
        "val ${it.name}: ($params) -> Unit = {},"
    }

    @Serializable
    data class StateProperties(
        val name: String,
        val type: String
    )

    @Serializable
    data class Action(
        val name: String,
        val params: List<String>
    )
}

