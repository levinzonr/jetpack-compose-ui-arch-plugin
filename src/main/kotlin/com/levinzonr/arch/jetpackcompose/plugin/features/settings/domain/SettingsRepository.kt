package com.levinzonr.arch.jetpackcompose.plugin.features.settings.domain

interface SettingsRepository {
   fun get(): Settings
   fun set(settings: Settings)
}
