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

    fun onOkButtonClick() {
        val properties = mutableMapOf(PropertyKeys.Name to name)
        generator.generateKt("ComposeContract", "${name}Contract", directory, properties)
        generator.generateKt("ComposeScreen", "${name}Screen", directory, properties)
        generator.generateKt("ComposeViewModel", "${name}ViewModel", directory, properties)
        generator.generateKt("ComposeCoordinator", "${name}Coordinator", directory, properties)
        scope.launch { successFlow.emit(Unit) }
    }

}