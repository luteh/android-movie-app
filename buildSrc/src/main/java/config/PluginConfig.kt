package config

import org.gradle.api.Project

internal fun Project.configureAppPlugin() = with(plugins) {
    apply(Plugins.androidApplication)
    apply(Plugins.daggerHilt)
}


internal fun Project.configureLibraryPlugin() = with(plugins) {
    apply(Plugins.androidLibrary)
    apply(Plugins.daggerHilt)
}