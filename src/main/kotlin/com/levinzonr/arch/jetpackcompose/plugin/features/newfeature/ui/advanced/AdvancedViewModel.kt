package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced

import com.levinzonr.arch.jetpackcompose.plugin.features.navigation.NavigationType
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models.ActionsType
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.repository.FeatureConfigurationRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models.InjectionConfiguration

class AdvancedViewModel(
    private val featureConfigurationRepository: FeatureConfigurationRepository
) {

    var useCollectFlowWithLifecycle: Boolean
        get() = featureConfigurationRepository.get().useCollectFlowWithLifecycle
        set(value) {
            val current = featureConfigurationRepository.get()
            featureConfigurationRepository.put(current.copy(useCollectFlowWithLifecycle = value))
        }

    var usePreviewParameterProvider: Boolean
        get() = featureConfigurationRepository.get().usePreviewParameterProvider
        set(value) {
            val current = featureConfigurationRepository.get()
            featureConfigurationRepository.put(current.copy(usePreviewParameterProvider = value))
        }


    private var injectionConfig
        get() = featureConfigurationRepository.get().injection
        set(value) {
            featureConfigurationRepository.update { it.copy(injection = value) }
        }

    private var navigationType: NavigationType
        get() = featureConfigurationRepository.get().navigationType
        set(value) {
            featureConfigurationRepository.put(featureConfigurationRepository.get().copy(navigationType = value))
        }

    private var actionsType: ActionsType
        get() = featureConfigurationRepository.get().actionsType
        set(value) {
            featureConfigurationRepository.put(featureConfigurationRepository.get().copy(actionsType = value))
        }


    var kiwiSetter: Boolean
        get() = navigationType == NavigationType.Kiwi
        set(value) {
            if (value) navigationType = NavigationType.Kiwi
        }

    var composeDestinationsSetter: Boolean
        get() = navigationType == NavigationType.ComposeDestinations
        set(value) {
            if (value) navigationType = NavigationType.ComposeDestinations
        }

    var jetpackNavSetter: Boolean
        get() = navigationType == NavigationType.Jetpack
        set(value) {
            if (value) navigationType = NavigationType.Jetpack
        }

    var koinSetter: Boolean
        get() = injectionConfig == InjectionConfiguration.Koin
        set(value) {
            if (value) {
                injectionConfig = InjectionConfiguration.Koin
            }
        }

    var hiltSetter: Boolean
        get() = injectionConfig == InjectionConfiguration.Hilt
        set(value) {
            if (value) injectionConfig = InjectionConfiguration.Hilt
        }


    var sealedActionsSetter: Boolean
        get() = actionsType == ActionsType.Sealed
        set(value) {
            if (value) actionsType = ActionsType.Sealed
        }

    var dataClassActionsSetter: Boolean
        get() = actionsType == ActionsType.Data
        set(value) {
            if (value) actionsType = ActionsType.Data
        }
}