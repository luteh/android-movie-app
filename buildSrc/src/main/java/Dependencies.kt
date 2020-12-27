object Libs {

    object Kotlin {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

//        val implementation = arrayOf(kotlinStdlib)
    }

    object Support {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        const val cardView = "androidx.cardview:cardview:${Versions.cardview}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"

        val implementation =
            arrayOf(appcompat, coreKtx, constraintLayout, cardView, recyclerView, material, fragmentKtx)
    }

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val viewmodelSavedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

        val implementation = arrayOf(extensions, livedataKtx, viewmodelKtx, viewmodelSavedState, commonJava8)
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

        val implementation = arrayOf(roomRuntime, roomKtx)
        val kapt = arrayOf(roomCompiler)
    }

    object Navigation {
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

        val implementation = arrayOf(navigationFragmentKtx, navigationUiKtx)
    }

    object Networking {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"

        val implementation = arrayOf(retrofit, converterGson, logginInterceptor, gson)
        val testImplementation = arrayOf(mockWebServer)
    }

    object DaggerHilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHiltAndroid}"
        const val hiltAndroidTesting =
            "com.google.dagger:hilt-android-testing:${Versions.daggerHiltAndroid}"
        const val hiltCommon = "androidx.hilt:hilt-common:${Versions.daggerHilt}"
        const val hiltViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHilt}"
        const val hiltAndroidCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltAndroid}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHilt}"

        val implementation = arrayOf(hiltAndroid, hiltCommon, hiltViewmodel)
        val kapt = arrayOf(hiltAndroidCompiler, hiltCompiler)
        val testImplementation = arrayOf(hiltAndroidTesting)
        val kaptTest = arrayOf(hiltAndroidCompiler)
        val androidTestImplementation = arrayOf(hiltAndroidTesting)
        val kaptAndroidTest = arrayOf(hiltAndroidCompiler)
    }

    object Coroutines {
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

        val implementation = arrayOf(coroutinesAndroid)
        val testImplementation = arrayOf(coroutinesTest)
    }

    object UnitTest {
        const val junit = "junit:junit:${Versions.junit}"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
        const val coreTesting = "androidx.arch.core:core-testing:${Versions.archCompoment}"

        val testImplementation = arrayOf(junit, mockitoKotlin, coreTesting)
    }

    object InstrumentTest {
        const val testJunit = "androidx.test.ext:junit:${Versions.androidxTestJunit}"
        const val testJunitKtx = "androidx.test.ext:junit-ktx:${Versions.androidxTestJunit}"
        const val espressoIdlingResource =
            "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val testCoreKtx = "androidx.test:core-ktx:${Versions.androidxTest}"
        const val testRules = "androidx.test:rules:${Versions.androidxTest}"

        val implementation = arrayOf(espressoIdlingResource)
        val androidTestImplementation = arrayOf(
            testJunit,
            testJunitKtx,
            espressoIdlingResource,
            espressoCore,
            espressoContrib,
            testCoreKtx,
            testRules
        )
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

        val implementation = arrayOf(glide)
        val kapt = arrayOf(glideCompiler)
    }

    object ThirdParty {
        const val youtubePlayerApi = "libs/YouTubeAndroidPlayerApi.jar"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val pageIndicatorView = "com.romandanylyk:pageindicatorview:${Versions.pageIndicatorView}"
    }
}

object Versions {
    // kotlin
    const val kotlin = "1.4.20"

    // build gradle
    const val buildGradle = "4.1.1"

    // support library
    const val appcompat = "1.2.0"
    const val coreKtx = "1.3.1"
    const val cardview = "1.0.0"
    const val recyclerview = "1.1.0"
    const val material = "1.2.0"
    const val constraint = "2.0.1"

    // architecture components
    const val fragment = "1.2.5"
    const val lifecycle = "2.2.0"
    const val room = "2.2.5"
    const val archCompoment = "2.1.0"
    const val navigation = "2.3.0"

    // di
    const val daggerHiltAndroid = "2.28.3-alpha"
    const val daggerHilt = "1.0.0-alpha02"

    // network
    const val retrofit = "2.9.0"
    const val okhttp = "4.8.1"

    // gson
    const val gson = "2.8.6"

    // coroutines
    const val coroutines = "1.3.7"

    // glide
    const val glide = "4.11.0"

    // page indicator
    const val pageIndicatorView = "1.0.3"

    // debugging
    const val timber = "4.7.1"

    // unit test
    const val junit = "4.12"
    const val androidxTest = "1.1.0"
    const val androidxTestJunit = "1.1.2"
    const val espresso = "3.1.0"
    const val mockitoKotlin = "2.2.0"
}