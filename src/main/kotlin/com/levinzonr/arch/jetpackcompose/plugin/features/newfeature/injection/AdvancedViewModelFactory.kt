package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.injection

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSourceImpl
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui.advanced.AdvancedViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.FeatureConfigurationRepository

object AdvancedViewModelFactory {

    fun create(dependencies: ProjectDependencies) : AdvancedViewModel {
        return AdvancedViewModel(
            FeatureConfigurationRepository(PreferencesDataSourceImpl(dependencies.properties))
        )
    }
}