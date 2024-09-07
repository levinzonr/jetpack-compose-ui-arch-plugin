package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.repository

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSource
import com.levinzonr.arch.jetpackcompose.plugin.features.navigation.NavigationType
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models.ActionsType
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models.FeatureConfiguration
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models.InjectionConfiguration

class FeatureConfigurationRepository(
    private val dataSource: PreferencesDataSource,
) {

    fun put(features: FeatureConfiguration) {
        dataSource.apply {
            put(KEY_INJECTION, features.injection.name)
            put(KEY_FLOW_LIFECYCLE, features.useCollectFlowWithLifecycle)
            put(KEY_PREVIEW_PARAMETER_PROVIDER, features.usePreviewParameterProvider)
            put(KEY_NAVIGATION_TYPE, features.navigationType.name)
            put(KEY_ACTIONS_TYPE, features.actionsType.name)
        }
    }

    fun get() : FeatureConfiguration {
        val injection = dataSource.get(KEY_INJECTION, InjectionConfiguration.Hilt.name)
        val navigationType = dataSource.get(KEY_NAVIGATION_TYPE, NavigationType.Kiwi.name)
        val actionsType = dataSource.get(KEY_ACTIONS_TYPE, ActionsType.Data.name)
        return FeatureConfiguration(
            useCollectFlowWithLifecycle = dataSource.get(KEY_FLOW_LIFECYCLE, true),
            usePreviewParameterProvider = dataSource.get(KEY_PREVIEW_PARAMETER_PROVIDER, true),
            injection = InjectionConfiguration.valueOf(injection),
            navigationType = NavigationType.valueOf(navigationType),
            actionsType = ActionsType.valueOf(actionsType)
        )
    }

    fun update(block: (FeatureConfiguration) -> FeatureConfiguration) {
        put(get().let(block))
    }

    companion object {
        private const val KEY_FLOW_LIFECYCLE = "use_flow_with_lifecycle"
        private const val KEY_PREVIEW_PARAMETER_PROVIDER = "use_preview_parameter_provider"
        private const val KEY_INJECTION = "view_model_injection"
        private const val KEY_NAVIGATION_TYPE = "navigation_type"
        private const val KEY_ACTIONS_TYPE = "actions_type"
    }
}