import plugin.MyDependencyPlugin

plugins {
    id(MyPluginId.androidApplication)
    id(MyPluginId.kotlinAndroid)
    id(MyPluginId.kapt)
    id(MyPluginId.daggerHilt)
    id(MyPluginId.navigationSafeargs)
    id(MyPluginId.myDependenciesPlugin)
}

android {
    compileSdkVersion(MyDependencyPlugin.configs.compileSdkVersion)
    buildToolsVersion = MyDependencyPlugin.configs.buildToolsVersion

    defaultConfig {
        applicationId = MyDependencyPlugin.configs.applicationId
        minSdkVersion(MyDependencyPlugin.configs.minSdkVersion)
        targetSdkVersion(MyDependencyPlugin.configs.targetSdkVersion)
        versionCode = MyDependencyPlugin.configs.versionCode
        versionName = MyDependencyPlugin.configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true

        manifestPlaceholders["appAuthRedirectScheme"] = Configs.applicationId
    }

    signingConfigs {
        create("release"){
            storeFile = file("${rootDir}/luteh-movie-keystore.jks")
            storePassword = "lutehmovie"
            keyAlias = "lutehmovie"
            keyPassword = "lutehmovie"
        }
    }

    buildTypes {
        getByName("release") {
//            isMinifyEnabled = true
//            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )

            signingConfig = signingConfigs.getByName("release")
        }

        getByName("debug") {
            isDebuggable = true
        }
    }

    kotlinOptions {
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

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))

    MyDependencyPlugin.modules.implementation.forEach { implementation(project(it)) }

    MyDependencyPlugin.support.implementation.forEach { implementation(it) }
    MyDependencyPlugin.lifecycle.implementation.forEach { implementation(it) }
    MyDependencyPlugin.navigation.implementation.forEach { implementation(it) }
    MyDependencyPlugin.networking.implementation.forEach { implementation(it) }
    MyDependencyPlugin.localStorage.implementation.forEach { implementation(it) }
    MyDependencyPlugin.localStorage.kapt.forEach { kapt(it) }
    MyDependencyPlugin.daggerHilt.implementation.forEach { implementation(it) }
    MyDependencyPlugin.daggerHilt.kapt.forEach { kapt(it) }

    implementation(MyDependencyPlugin.thirdParty.timber)

    implementation(MyDependencyPlugin.glide.glide)
    kapt(MyDependencyPlugin.glide.glideCompiler)

    MyDependencyPlugin.unitTest.testImplementation.forEach { testImplementation(it) }
    MyDependencyPlugin.instrumentTest.androidTestImplementation.forEach {
        androidTestImplementation(
            it
        )
    }

    implementation("androidx.webkit:webkit:1.4.0")
}