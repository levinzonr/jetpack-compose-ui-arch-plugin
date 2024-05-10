package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.models

import com.levinzonr.arch.jetpackcompose.plugin.core.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.FeatureBreakdown

data class FeatureProperties(
    val name: String,
    val config: FeatureConfiguration,
    val breakdown: FeatureBreakdown?
) {

    fun toProperties(): MutableMap<String, Any> {
        return mutableMapOf(
            PropertyKeys.Name to name,
            PropertyKeys.UseFlowWithLifecycle to config.useCollectFlowWithLifecycle,
            PropertyKeys.VIEW_MODEL_INJECTION to config.injection.name,
            PropertyKeys.STATE_PROPS to breakdown?.propertyStatements.orEmpty(),
            PropertyKeys.ACTIONS to breakdown?.actionStatements.orEmpty(),
            PropertyKeys.ACTIONS_HANDLERS to breakdown?.actionHandlers.orEmpty(),
            PropertyKeys.AI_USED to (breakdown != null),
            PropertyKeys.UsePreviewParameterProvider to config.usePreviewParameterProvider,
            PropertyKeys.VIEW_MODEL_INJECTION to config.injection.name,
            PropertyKeys.COORDINATOR_ACTIONS to breakdown?.coordinatorActions.orEmpty(),
            "VM_ACTIONS" to breakdown?.viewModelActions.orEmpty()
        )
    }
}
