package com.levinzonr.arch.jetpackcompose.plugin.core

import io.github.classgraph.ClassGraph
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.URL


/**
 * This work in (JUnit, Jar, etc.)
 * It's better to create a new instance each time
 *
 * @author Aleksei Vlasov (github.com/adideas)
 */
class IntellijIdeaResourceLoader {
    var dir: String? = null

    init {
        if (Thread.currentThread().contextClassLoader == null) {
            // without jar
            dir = System.getProperty("user.dir")
            if (dir == null) {
                val currentDirFile = File(".")
                val helper = currentDirFile.absolutePath
                try {
                    dir = helper.substring(0, helper.length - currentDirFile.canonicalPath.length)
                } catch (ignore: Exception) {
                }
            }
            dir = File(dir, "resource").absolutePath
        }
    }

    fun getResource(anyClassFromYouProject: Class<*>, filePath: String?): URL? {
        if (dir != null) {
            try {
                return URL("file://" + File(dir, filePath).absolutePath)
            } catch (ignore: Exception) {
            }
        }
        var url: URL? = null
        url = anyClassFromYouProject.classLoader.getResource(filePath)
        if (url != null) {
            return url
        }

        Thread.currentThread().contextClassLoader = anyClassFromYouProject.classLoader
        url = Thread.currentThread().contextClassLoader.getResource(filePath)
        if (url != null) {
            return url
        }

        val resources = ClassGraph().acceptPaths(filePath).scan().allResources
        if (!resources.isEmpty()) {
            resources[0].uri
        }
        return null
    }

    fun getResourceAsStream(anyClassFromYouProject: Class<*>, filePath: String?): InputStream? {
        val url = getResource(anyClassFromYouProject, filePath)
        if (url != null) {
            try {
                return url.openStream()
            } catch (ignore: IOException) {
            }
        }

        return null
    }
}


inline fun <reified T> T.loadResource(filePath: String): InputStream? {
    return IntellijIdeaResourceLoader().getResourceAsStream(T::class.java, filePath)
}

inline fun <reified T> T.loadStringResource(filePath: String): String? {
    return loadResource(filePath)
        ?.readAllBytes()
        ?.toString(Charsets.UTF_8)
}