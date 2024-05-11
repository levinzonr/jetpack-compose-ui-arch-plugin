package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.injection

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSourceImpl
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.repository.FeatureConfigurationRepository

object ExperimentalFeaturesRepositoryFactory {

    fun create(dependencies: ProjectDependencies) : FeatureConfigurationRepository {
        return FeatureConfigurationRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}