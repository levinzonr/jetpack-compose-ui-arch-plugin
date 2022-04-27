package com.levinzonr.jetpackcompose.arch

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory

class ComposeArchAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val generator = TemplateGenerator(e.project!!)
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        ComposeArchDialog(e.project!!).show { name ->
            val properties = mutableMapOf("NAME" to name)
            generator.generateKt("ComposeContract", "${name}Contract", directory, properties)
            generator.generateKt("ComposeScreen", "${name}Screen", directory, properties)
            generator.generateKt("ComposeViewModel", "${name}ViewModel", directory, properties)
            generator.generateKt("ComposeCoordinator", "${name}Coordinator", directory, properties)
        }
    }
}