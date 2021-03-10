package config

import org.gradle.api.Project

internal fun Project.configureAppPlugin() = with(plugins) {
    apply(MyPluginId.androidApplication)
    apply(MyPluginId.daggerHilt)
}


internal fun Project.configureLibraryPlugin() = with(plugins) {
    apply(MyPluginId.androidLibrary)
    apply(MyPluginId.daggerHilt)
}