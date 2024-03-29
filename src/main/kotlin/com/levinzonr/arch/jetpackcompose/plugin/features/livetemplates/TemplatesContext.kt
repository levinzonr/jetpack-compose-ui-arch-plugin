package com.levinzonr.arch.jetpackcompose.plugin.features.livetemplates

import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType


class TemplatesContext : TemplateContextType(ID, Name) {
    override fun isInContext(context: TemplateActionContext): Boolean {
        return context.file.name.endsWith(".kt")
    }

    companion object {
        private const val ID = "com.levinzonr.compose.livetemplate.context"
        private const val Name = "Compose Live Templates Context"
    }
}
