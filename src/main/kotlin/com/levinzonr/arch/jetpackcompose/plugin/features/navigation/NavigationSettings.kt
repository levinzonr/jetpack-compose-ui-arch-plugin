package com.levinzonr.arch.jetpackcompose.plugin.features.navigation

data class NavigationSettings(
    val classSuffix: String = "Destination",
    val type: NavigationType = NavigationType.Kiwi,
) {
    enum class NavigationType {
        Kiwi, ComposeDestinations
    }
}