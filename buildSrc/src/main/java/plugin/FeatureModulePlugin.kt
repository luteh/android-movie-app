//package plugin
//
//import config.configureKotlinAndroidPlugins
//import config.configureLibraryAndroid
//import dependencies.configureFeatureDependencies
//import org.gradle.api.Project
//
//open class FeatureModulePlugin : BaseLibraryModulePlugin() {
//    override fun Project.applyModuleDependencies() {
//        configureKotlinAndroidPlugins(isNotFeatureModule = false)
//        configureFeatureDependencies()
//    }
//
//    override fun Project.applyLibraryAndroid() {
//        configureLibraryAndroid()
//    }
//}