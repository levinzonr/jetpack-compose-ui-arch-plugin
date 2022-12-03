package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.injection

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSourceImpl
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.ExperimentalFeaturesRepository

object ExperimentalFeaturesRepositoryFactory {

    fun create(dependencies: ProjectDependencies) : ExperimentalFeaturesRepository {
        return ExperimentalFeaturesRepository(
            dataSource = PreferencesDataSourceImpl(
                component = dependencies.properties
            )
        )
    }
}