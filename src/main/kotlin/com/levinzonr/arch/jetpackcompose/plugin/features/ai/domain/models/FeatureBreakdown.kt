package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class FeatureBreakdown(
    val properties: List<StateProperties>,
    val actions: List<Action>
) {

    val propertyStatements = properties.joinToString("\n") {
        val defaultValue = if (it.defaultValue == "") "\"\"" else it.defaultValue
        "val ${it.name}: ${it.type} = ${defaultValue},"
    }

    val actionStatements= actions.joinToString("\n") {
        val params = it.params.joinToString(",")
        "val ${it.name}: ($params) -> Unit = {},"
    }

    val actionHandlers = actions.joinToString("\n") {
        "${it.name} = coordinator::handle${it.name.drop(2)},"
    }

    val coordinatorActions = actions.joinToString("\n") {
        val params = it.params.joinToString(",") {
            "${it.lowercase()}: $it"
        }
        "fun handle${it.name.drop(2)}($params) { \n } "
    }

    @Serializable
    data class StateProperties(
        val name: String,
        val type: String,
        val defaultValue: String
    )

    @Serializable
    data class Action(
        val name: String,
        val params: List<String>
    )
}

