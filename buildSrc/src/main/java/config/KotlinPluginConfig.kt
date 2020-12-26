package config

import org.gradle.api.Project

internal fun Project.configureKotlinAndroidPlugins(isNotFeatureModule: Boolean = true) = with(plugins) {
    apply(Plugins.kotlinAndroid)
    apply(Plugins.kotlinExtensions)
    //    apply(Plugins.kotlinParcelize)
    apply(Plugins.kapt)
    if (isNotFeatureModule) apply(Plugins.navigationSafeargs)
}