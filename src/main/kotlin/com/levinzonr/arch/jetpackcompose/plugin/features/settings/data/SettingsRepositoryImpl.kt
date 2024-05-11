package com.levinzonr.arch.jetpackcompose.plugin.features.settings.data

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSource
import com.levinzonr.arch.jetpackcompose.plugin.features.ollama.OllamaSettings
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.Settings
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.SettingsRepository

class SettingsRepositoryImpl(
    private val dataSource: PreferencesDataSource
): SettingsRepository {


    override fun get(): Settings {
        val default = Settings.DEFAULT
        val host = dataSource.get(KEY_OLLAMA_HOST, default.ollama.host)
        val model = dataSource.get(KEY_OLLAMA_MODEL, default.ollama.model)
        val timeout = dataSource.get(KEY_OLLAMA_TIMEOUT, default.ollama.timeoutSeconds.toString()).toLong()
        return Settings(
            ollama = OllamaSettings(host, model, timeout)
        )
    }

    override fun set(settings: Settings) {
        dataSource.put(KEY_OLLAMA_HOST, settings.ollama.host)
        dataSource.put(KEY_OLLAMA_MODEL, settings.ollama.model)
        dataSource.put(KEY_OLLAMA_TIMEOUT, settings.ollama.timeoutSeconds.toString())
    }

    companion object {
        private const val KEY_OLLAMA_HOST = "settings:ollama:host"
        private const val KEY_OLLAMA_MODEL = "settings:ollama:model"
        private const val KEY_OLLAMA_TIMEOUT = "settings:ollama:timeout"
    }
}