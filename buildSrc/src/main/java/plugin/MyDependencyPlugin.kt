package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import MyPluginId
import Configs
import Modules

class MyDependencyPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // Possibly common dependencies or can stay empty
    }

    companion object {
        val kotlin = Libs.Kotlin
        val support = Libs.Support
        val lifecycle = Libs.Lifecycle
        val localStorage = Libs.LocalStorage
        val navigation = Libs.Navigation
        val networking = Libs.Networking
        val daggerHilt = Libs.DaggerHilt
        val coroutines = Libs.Coroutines
        val unitTest = Libs.UnitTest
        val instrumentTest = Libs.InstrumentTest
        val glide = Libs.Glide
        val thirdParty = Libs.ThirdParty

        val pluginId = MyPluginId
        val configs = Configs
        val modules = Modules
    }
}