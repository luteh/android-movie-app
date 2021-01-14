package config

import Configs
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import java.io.FileInputStream
import java.util.*


internal fun Project.configureAndroid() = this.extensions.getByType<AppExtension>().apply {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        applicationId = Configs.applicationId
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    configure<AndroidExtensionsExtension> {
        isExperimental = true
    }
}

internal fun Project.configureLibraryAndroid(buildConfigCallback: (DefaultConfig) -> Unit = {}) =
    this.extensions.getByType<LibraryExtension>().apply {
        compileSdkVersion(Configs.compileSdkVersion)
        buildToolsVersion = Configs.buildToolsVersion

        val apikeyPropertiesFile = rootProject.file("apikey.properties")
        val apikeyProperties = Properties()
        apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

        defaultConfig {
            minSdkVersion(Configs.minSdkVersion)
            targetSdkVersion(Configs.targetSdkVersion)
            versionCode = Configs.versionCode
            versionName = Configs.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            buildConfigCallback.invoke(this)

            buildConfigField("String", "MOVIEDB_API_KEY", apikeyProperties.getProperty("MOVIEDB_API_KEY"))
            buildConfigField("String", "GOOGLE_API_KEY", apikeyProperties.getProperty("GOOGLE_API_KEY"))

            buildConfigField("String", "BASE_URL_MOVIEDB", String.format("\"%s\"", "https://api.themoviedb.org/3/"))
            buildConfigField("String", "BASE_URL_IMAGE_POSTER", "\"https://image.tmdb.org/t/p/w185/\"")
            buildConfigField("String", "BASE_URL_IMAGE_BACKDROP", "\"https://image.tmdb.org/t/p/w780/\"")
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }

            getByName("debug") {
                isDebuggable = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures {
            viewBinding = true
        }
    }



private fun Project.buildProperty(key: String, format: Boolean = true): String {
    return if (format) {
        String.format("\"%s\"", project.property(key) as String)
    } else {
        project.property(key) as String
    }
}
