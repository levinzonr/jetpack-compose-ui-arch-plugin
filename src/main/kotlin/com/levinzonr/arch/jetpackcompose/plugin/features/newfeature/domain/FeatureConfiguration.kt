package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain

data class FeatureConfiguration(
    val useCollectFlowWithLifecycle: Boolean,
    val usePreviewParameterProvider: Boolean,
    val injection: InjectionConfiguration
)