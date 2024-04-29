package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced

import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.FeatureConfigurationRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.InjectionConfiguration

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

}