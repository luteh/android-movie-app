import java.util.*
import java.io.FileInputStream
import plugin.MyDependencyPlugin

plugins {
    id(MyPluginId.androidLibrary)
    id(MyPluginId.kotlinAndroid)
    id(MyPluginId.kotlinParcelize)
    id(MyPluginId.kapt)
    id(MyPluginId.daggerHilt)
    id(MyPluginId.navigationSafeargs)
    id(MyPluginId.myDependenciesPlugin)
}

android {
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

        buildConfigField(
            "String",
            "MOVIEDB_API_KEY",
            apikeyProperties.getProperty("MOVIEDB_API_KEY")
        )
        buildConfigField("String", "GOOGLE_API_KEY", apikeyProperties.getProperty("GOOGLE_API_KEY"))

        buildConfigField(
            "String",
            "BASE_URL_MOVIEDB",
            String.format("\"%s\"", "https://api.themoviedb.org/3/")
        )
        buildConfigField("String", "BASE_URL_IMAGE_POSTER", "\"https://image.tmdb.org/t/p/w185/\"")
        buildConfigField(
            "String",
            "BASE_URL_IMAGE_BACKDROP",
            "\"https://image.tmdb.org/t/p/w780/\""
        )
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

    MyDependencyPlugin.support.implementation.forEach { implementation(it) }
    MyDependencyPlugin.lifecycle.implementation.forEach { implementation(it) }
    MyDependencyPlugin.daggerHilt.implementation.forEach { implementation(it) }
    MyDependencyPlugin.daggerHilt.kapt.forEach { kapt(it) }
    MyDependencyPlugin.navigation.implementation.forEach { implementation(it) }
    MyDependencyPlugin.coroutines.implementation.forEach { implementation(it) }
    MyDependencyPlugin.networking.implementation.forEach { implementation(it) }
    MyDependencyPlugin.localStorage.implementation.forEach { implementation(it) }
    MyDependencyPlugin.localStorage.kapt.forEach { kapt(it) }

    MyDependencyPlugin.unitTest.testImplementation.forEach { testImplementation(it) }
    MyDependencyPlugin.instrumentTest.implementation.forEach { implementation(it) }
    MyDependencyPlugin.instrumentTest.androidTestImplementation.forEach {
        androidTestImplementation(
            it
        )
    }

    implementation(MyDependencyPlugin.glide.glide)
    kapt(MyDependencyPlugin.glide.glideCompiler)

    implementation(MyDependencyPlugin.thirdParty.timber)
}