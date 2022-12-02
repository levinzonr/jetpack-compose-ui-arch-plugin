package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.TemplateGenerator
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import com.levinzonr.arch.jetpackcompose.plugin.features.newfeature.domain.ExperimentalFeaturesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow

class ComposeArchDialogViewModel(
    private val directory: PsiDirectory,
    private val generator: TemplateGenerator,
    private val repository: ExperimentalFeaturesRepository,
    private val editorManager: FileEditorManager,
) : BaseViewModel() {


    var name: String = ""
        get() = field.capitalize()

    var flowWithLifecycleEnabled: Boolean
        get() = repository.get().useCollectFlowWithLifecycle
        set(value) = repository.put(repository.get().copy(useCollectFlowWithLifecycle = value))

    val successFlow = MutableSharedFlow<Unit>()

    var createFeaturePackage: Boolean = true

    fun onOkButtonClick() {
        val properties = mutableMapOf(PropertyKeys.Name to name)
        val featPackage = if (createFeaturePackage) directory.createSubdirectory(name.lowercase()) else directory
        val file = generator.generateKt("ComposeContract", "${name}Contract", featPackage, properties)
        generator.generateKt("ComposeScreen", "${name}Screen", featPackage, properties)
        generator.generateKt("ComposeViewModel", "${name}ViewModel", featPackage, properties)
        generator.generateKt("ComposeCoordinator", "${name}Coordinator", featPackage, properties)
        generator.generateKt("ComposeRoute", "${name}Route", featPackage, properties)

        if (featPackage.findSubdirectory("components") == null) {
            featPackage.createSubdirectory("components")
        }

        editorManager.openFile(file.virtualFile, true)
        scope.launch { successFlow.emit(Unit) }
    }

}