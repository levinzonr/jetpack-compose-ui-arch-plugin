package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSource

class ExperimentalFeaturesRepository(
    private val dataSource: PreferencesDataSource
) {

    fun put(features: ExperimentalFeatures) {
        dataSource.put(FlowWithLifecycleKey, features.useCollectFlowWithLifecycle)
    }

    fun get() : ExperimentalFeatures {
        return ExperimentalFeatures(
            useCollectFlowWithLifecycle = dataSource.get(FlowWithLifecycleKey)
        )
    }

    companion object {
        private const val FlowWithLifecycleKey = "use_flow_with_lifecycle"
    }
}