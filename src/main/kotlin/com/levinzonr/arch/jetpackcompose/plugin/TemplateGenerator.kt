package com.levinzonr.arch.jetpackcompose.plugin

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.newvfs.impl.VirtualDirectoryImpl
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.file.PsiDirectoryFactory
import java.util.*

class TemplateGenerator(private val project: Project) {

    fun generateKt(
            templateName: String,
            fileName: String,
            directory: PsiDirectory,
            properties: MutableMap<String,Any>
    ) : PsiFile {

        val existing = directory.findFile("${fileName}.kt")
        if (existing != null) return existing


        val manager = FileTemplateManager.getInstance(project)
        val template = manager.getInternalTemplate(templateName)
        properties[PropertyKeys.PackageName] = requireNotNull(directory.getPackageName())
        return FileTemplateUtil.createFromTemplate(
                template,
                "${fileName}.kt",
                properties.toProperties(),
                directory
        ) as PsiFile
    }

    private fun PsiDirectory.getPackageName(): String? {
        return ProjectRootManager.getInstance(project)
                .fileIndex
                .getPackageNameByDirectory(virtualFile)
    }


    private fun Map<String, Any>.toProperties(): Properties {
        return Properties().apply {
            this@toProperties.forEach { setProperty(it.key, it.value.toString()) }
        }
    }
}

