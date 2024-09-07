package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models

import com.levinzonr.arch.jetpackcompose.plugin.features.navigation.NavigationType


data class FeatureConfiguration(
    val useCollectFlowWithLifecycle: Boolean,
    val usePreviewParameterProvider: Boolean,
    val injection: InjectionConfiguration,
    val navigationType: NavigationType
)