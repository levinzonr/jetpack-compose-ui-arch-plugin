package com.levinzonr.arch.jetpackcompose.plugin.ui

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.TemplateGenerator
import com.levinzonr.arch.jetpackcompose.plugin.base.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.ProjectDependencies
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow

class ComposeArchDialogViewModel(
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel() {

    private val generator = projectDependencies.generator

    var name: String = ""
        get() = field.capitalize()
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

        projectDependencies.editor.openFile(file.virtualFile, true)
        scope.launch { successFlow.emit(Unit) }
    }

}