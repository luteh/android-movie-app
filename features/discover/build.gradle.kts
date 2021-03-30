import java.util.*
import java.io.FileInputStream
import plugin.MyDependencyPlugin

plugins {
    id(MyPluginId.androidLibrary)
    id(MyPluginId.kotlinAndroid)
    id(MyPluginId.kapt)
    id(MyPluginId.daggerHilt)
    id(MyPluginId.myDependenciesPlugin)
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
//            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isDebuggable = true
        }
    }

    kotlinOptions{
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies{
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))

    implementation(project(Modules.core))

    MyDependencyPlugin.support.implementation.forEach { implementation(it) }
    MyDependencyPlugin.lifecycle.implementation.forEach { implementation(it) }
    MyDependencyPlugin.daggerHilt.implementation.forEach { implementation(it) }
    MyDependencyPlugin.daggerHilt.kapt.forEach { kapt(it) }
    MyDependencyPlugin.navigation.implementation.forEach { implementation(it) }
    MyDependencyPlugin.coroutines.implementation.forEach { implementation(it) }

    MyDependencyPlugin.unitTest.testImplementation.forEach { testImplementation(it) }
    MyDependencyPlugin.instrumentTest.androidTestImplementation.forEach {
        androidTestImplementation(
            it
        )
    }

    implementation(MyDependencyPlugin.glide.glide)
    kapt(MyDependencyPlugin.glide.glideCompiler)

    implementation(MyDependencyPlugin.thirdParty.timber)
    implementation(MyDependencyPlugin.thirdParty.pageIndicatorView)
}