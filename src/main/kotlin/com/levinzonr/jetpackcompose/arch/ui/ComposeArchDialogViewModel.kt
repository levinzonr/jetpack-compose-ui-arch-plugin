package com.levinzonr.jetpackcompose.arch.ui

import com.intellij.psi.PsiDirectory
import com.levinzonr.jetpackcompose.arch.PropertyKeys
import com.levinzonr.jetpackcompose.arch.TemplateGenerator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class ComposeArchDialogViewModel(
    private val generator: TemplateGenerator,
    private val directory: PsiDirectory,
) {
    var name: String = ""
    val successFlow = MutableSharedFlow<Unit>()
    private val scope = CoroutineScope(Dispatchers.Main)


    fun onOkButtonClick() {
        val properties = mutableMapOf(PropertyKeys.Name to name)
        generator.generateKt("ComposeContract", "${name}Contract", directory, properties)
        generator.generateKt("ComposeScreen", "${name}Screen", directory, properties)
        generator.generateKt("ComposeViewModel", "${name}ViewModel", directory, properties)
        generator.generateKt("ComposeCoordinator", "${name}Coordinator", directory, properties)
        scope.launch { successFlow.emit(Unit) }
    }

}