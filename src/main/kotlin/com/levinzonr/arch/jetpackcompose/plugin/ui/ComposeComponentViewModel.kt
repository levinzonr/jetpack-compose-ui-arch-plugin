package com.levinzonr.arch.jetpackcompose.plugin.ui

import com.intellij.psi.PsiDirectory
import com.levinzonr.arch.jetpackcompose.plugin.PropertyKeys
import com.levinzonr.arch.jetpackcompose.plugin.TemplateGenerator
import com.levinzonr.arch.jetpackcompose.plugin.base.BaseViewModel
import com.levinzonr.arch.jetpackcompose.plugin.dependencies.Dependencies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ComposeComponentViewModel(
        private val directory: PsiDirectory,
        private val generator: TemplateGenerator
) : BaseViewModel(){
    var name: String = ""
        get() = field.capitalize()
    val successFlow = MutableSharedFlow<Unit>()

    fun onOkButtonClick() {
        val properties = mutableMapOf(PropertyKeys.Name to name)
        generator.generateKt("ComposeComponent", name, directory, properties)
        scope.launch { successFlow.emit(Unit) }
    }
}
