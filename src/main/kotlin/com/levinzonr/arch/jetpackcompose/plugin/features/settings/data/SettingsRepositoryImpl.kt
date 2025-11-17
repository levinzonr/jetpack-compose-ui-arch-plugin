package com.levinzonr.arch.jetpackcompose.plugin.features.settings.data

import com.levinzonr.arch.jetpackcompose.plugin.core.persistence.PreferencesDataSource
import com.levinzonr.arch.jetpackcompose.plugin.features.navigation.NavigationSettings
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.ollama.OllamaSettings
import com.levinzonr.arch.jetpackcompose.plugin.features.ai.data.openai.OpenAISettings
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.AIClientType
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.Settings
import com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain.SettingsRepository
import com.levinzonr.arch.jetpackcompose.plugin.features.ui.UILibrarySettings
import com.levinzonr.arch.jetpackcompose.plugin.features.ui.UILibraryType

class SettingsRepositoryImpl(
    private val dataSource: PreferencesDataSource
): SettingsRepository {


    override fun get(): Settings {
        val default = Settings.DEFAULT
        val selectedAIClient = try {
            AIClientType.valueOf(dataSource.get(KEY_SELECTED_AI_CLIENT, default.selectedAIClient.name))
        } catch (e: IllegalArgumentException) {
            default.selectedAIClient
        }
        
        val host = dataSource.get(KEY_OLLAMA_HOST, default.ollama.host)
        val model = dataSource.get(KEY_OLLAMA_MODEL, default.ollama.model)
        val timeout = dataSource.get(KEY_OLLAMA_TIMEOUT, default.ollama.timeoutSeconds.toString()).toLong()

        val openaiApiKey = dataSource.get(KEY_OPENAI_API_KEY, default.openai.apiKey)
        val openaiTimeout = dataSource.get(KEY_OPENAI_TIMEOUT, default.openai.timeoutSeconds.toString()).toInt()
        val openaiModelId = dataSource.get(KEY_OPENAI_MODEL_ID, default.openai.modelId)
        val openaiHost = dataSource.get(KEY_OPENAI_HOST, default.openai.host)

        val navSuffix = dataSource.get(KEY_NAV_SUFFIX, default.navigationSettings.classSuffix)

        val uiLibraryType = try {
            UILibraryType.valueOf(dataSource.get(KEY_UI_LIBRARY_TYPE, default.uiLibrarySettings.type.name))
        } catch (e: IllegalArgumentException) {
            default.uiLibrarySettings.type
        }

        return Settings(
            selectedAIClient = selectedAIClient,
            ollama = OllamaSettings(host, model, timeout),
            openai = OpenAISettings(openaiApiKey, openaiTimeout, openaiModelId, openaiHost),
            navigationSettings = NavigationSettings(navSuffix),
            uiLibrarySettings = UILibrarySettings(uiLibraryType)
        )
    }

    override fun set(settings: Settings) {
        dataSource.put(KEY_SELECTED_AI_CLIENT, settings.selectedAIClient.name)
        
        dataSource.put(KEY_OLLAMA_HOST, settings.ollama.host)
        dataSource.put(KEY_OLLAMA_MODEL, settings.ollama.model)
        dataSource.put(KEY_OLLAMA_TIMEOUT, settings.ollama.timeoutSeconds.toString())
        
        dataSource.put(KEY_OPENAI_API_KEY, settings.openai.apiKey)
        dataSource.put(KEY_OPENAI_TIMEOUT, settings.openai.timeoutSeconds.toString())
        dataSource.put(KEY_OPENAI_MODEL_ID, settings.openai.modelId)
        dataSource.put(KEY_OPENAI_HOST, settings.openai.host)
        
        dataSource.put(KEY_NAV_SUFFIX, settings.navigationSettings.classSuffix)
        
        dataSource.put(KEY_UI_LIBRARY_TYPE, settings.uiLibrarySettings.type.name)
    }

    companion object {
        private const val KEY_SELECTED_AI_CLIENT = "settings:ai:selectedClient"
        
        private const val KEY_OLLAMA_HOST = "settings:ollama:host"
        private const val KEY_OLLAMA_MODEL = "settings:ollama:model"
        private const val KEY_OLLAMA_TIMEOUT = "settings:ollama:timeout"

        private const val KEY_OPENAI_API_KEY = "settings:openai:apiKey"
        private const val KEY_OPENAI_TIMEOUT = "settings:openai:timeout"
        private const val KEY_OPENAI_MODEL_ID = "settings:openai:modelId"
        private const val KEY_OPENAI_HOST = "settings:openai:host"

        private const val KEY_NAV_SUFFIX = "settings:navigation:suffix"

        private const val KEY_UI_LIBRARY_TYPE = "settings:ui:libraryType"
    }
}