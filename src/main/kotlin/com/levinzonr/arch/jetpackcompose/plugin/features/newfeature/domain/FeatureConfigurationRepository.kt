package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSource

class FeatureConfigurationRepository(
    private val dataSource: PreferencesDataSource
) {

    fun put(features: FeatureConfiguration) {
        dataSource.apply {
            put(KEY_INJECTION, features.injection.name)
            put(KEY_FLOW_LIFECYCLE, features.useCollectFlowWithLifecycle)
            put(KEY_PREVIEW_PARAMETER_PROVIDER, features.usePreviewParameterProvider)
        }
    }

    fun get() : FeatureConfiguration {
        val injection = dataSource.get(KEY_INJECTION, InjectionConfiguration.Hilt.name)
        return FeatureConfiguration(
            useCollectFlowWithLifecycle = dataSource.get(KEY_FLOW_LIFECYCLE, true),
            usePreviewParameterProvider = dataSource.get(KEY_PREVIEW_PARAMETER_PROVIDER, true),
            injection = InjectionConfiguration.valueOf(injection)
        )
    }

    fun update(block: (FeatureConfiguration) -> FeatureConfiguration) {
        put(get().let(block))
    }

    companion object {
        private const val KEY_FLOW_LIFECYCLE = "use_flow_with_lifecycle"
        private const val KEY_PREVIEW_PARAMETER_PROVIDER = "use_preview_parameter_provider"
        private const val KEY_INJECTION = "view_model_injection"
    }
}