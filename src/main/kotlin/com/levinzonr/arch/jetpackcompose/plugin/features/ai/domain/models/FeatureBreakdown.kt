package com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class FeatureBreakdown(
    val properties: List<StateProperties>,
    val actions: List<Action>
) {

    @Serializable
    class StateProperties(
        val name: String,
        val type: String
    )

    @Serializable
    class Action(
        val name: String
    )
}

