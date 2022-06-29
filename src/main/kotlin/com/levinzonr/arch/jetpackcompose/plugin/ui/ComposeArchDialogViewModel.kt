package com.levinzonr.arch.jetpackcompose.plugin.ui

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.TemplateGenerator
import com.levinzonr.arch.jetpackcompose.plugin.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow

class ComposeArchDialogViewModel(
    private val generator: TemplateGenerator,
    private val directory: PsiDirectory,
) : BaseViewModel() {
    var name: String = ""
        get() = field.capitalize()
    val successFlow = MutableSharedFlow<Unit>()

    var createFeaturePackage: Boolean = true

    fun onOkButtonClick() {
        val properties = mutableMapOf(PropertyKeys.Name to name)
        val featPackage = if (createFeaturePackage) directory.createSubdirectory(name.lowercase()) else directory
        generator.generateKt("ComposeContract", "${name}Contract", featPackage, properties)
        generator.generateKt("ComposeScreen", "${name}Screen", featPackage, properties)
        generator.generateKt("ComposeViewModel", "${name}ViewModel", featPackage, properties)
        generator.generateKt("ComposeCoordinator", "${name}Coordinator", featPackage, properties)
        featPackage.createSubdirectory("components")
        scope.launch { successFlow.emit(Unit) }
    }

}