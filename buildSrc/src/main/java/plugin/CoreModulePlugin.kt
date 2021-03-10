//package plugin
//
//import config.configureKotlinAndroidPlugins
//import org.gradle.api.Project
//import dependencies.configureCoreDependencies
//import config.configureLibraryAndroid
//
//open class CoreModulePlugin : BaseLibraryModulePlugin() {
//    override fun Project.applyModuleDependencies() {
//        configureKotlinAndroidPlugins()
//        configureCoreDependencies()
//    }
//
//    override fun Project.applyLibraryAndroid() {
//        configureLibraryAndroid()
//    }
//}