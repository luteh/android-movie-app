package config

import org.gradle.api.Project

internal fun Project.configureKotlinAndroidPlugins(isNotFeatureModule: Boolean = true) = with(plugins) {
    apply(MyPluginId.kotlinAndroid)
    apply(MyPluginId.kotlinExtensions)
    //    apply(Plugins.kotlinParcelize)
    apply(MyPluginId.kapt)
    if (isNotFeatureModule) apply(MyPluginId.navigationSafeargs)
}