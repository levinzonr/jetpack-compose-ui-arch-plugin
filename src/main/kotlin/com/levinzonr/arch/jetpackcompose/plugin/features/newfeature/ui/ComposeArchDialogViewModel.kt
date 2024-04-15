package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.ui

import com.intellij.openapi.application.Application
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.core.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.core.TemplateGenerator
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.FeatureBreakdownRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.domain.models.FeatureBreakdown
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.FeatureConfigurationRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.swing.Swing

class ComposeArchDialogViewModel(
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: FeatureConfigurationRepository,
    private val editorManager: FileEditorManager,
    private val application: Application,
    private val featureBreakdownRepository: FeatureBreakdownRepository
) : BaseViewModel() {

    var name: String = ""
        get() = field.capitalize()

    val successFlow = MutableSharedFlow<Unit>()


    var description: String = ""

    var createFeaturePackage: Boolean = true

    fun onOkButtonClick() {


        var snippet: FeatureBreakdown?

        scope.launch {
            if (description.isNotBlank()) {
                val state = featureBreakdownRepository.generate(name, description)
                println(state)
                snippet = state.getOrNull()
                generateFiles(snippet)
            } else {
                generateFiles(null)
            }
        }
    }

    private fun generateFiles(snippet: FeatureBreakdown?) {
        val config = repository.get()
        val properties = mutableMapOf<String, Any>(
            PropertyKeys.Name to name,
            PropertyKeys.UseFlowWithLifecycle to config.useCollectFlowWithLifecycle,
            PropertyKeys.VIEW_MODEL_INJECTION to config.injection.name
        )

        application.runWriteAction {
            val featPackage =
                if (createFeaturePackage) directory.createSubdirectory(name.lowercase()) else directory
            val file = generator.generateKt("ComposeContract", "${name}Contract", featPackage, properties)
            generator.generateKt("ComposeScreen", "${name}Screen", featPackage, properties)
            generator.generateKt("ComposeViewModel", "${name}ViewModel", featPackage, properties)
            generator.generateKt("ComposeCoordinator", "${name}Coordinator", featPackage, properties)
            generator.generateKt("ComposeRoute", "${name}Route", featPackage, properties)

            if (featPackage.findSubdirectory("components") == null) {
                featPackage.createSubdirectory("components")
            }

            editorManager.openFile(file.virtualFile, true)
        }
        scope.launch { successFlow.emit(Unit) }
    }

}