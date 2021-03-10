//package plugin
//
//import config.configureAndroid
//import config.configureAppPlugin
//import config.configureKotlinAndroidPlugins
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.kotlin.dsl.withType
//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import dependencies.configureAppDependencies
//
//open class AppModulePlugin : Plugin<Project> {
//
//    override fun apply(target: Project) {
//        target.configureAppPlugin()
//        target.configureKotlinAndroidPlugins()
//        target.configureAndroid()
//        target.tasks.withType<KotlinCompile> {
//            kotlinOptions {
//                jvmTarget = "1.8"
//            }
//        }
//        target.configureAppDependencies()
//    }
//}