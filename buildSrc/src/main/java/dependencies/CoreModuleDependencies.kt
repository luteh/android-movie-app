package dependencies

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCoreDependencies() = dependencies {
    add(
        ConfigurationType.IMPLEMENTATION,
        fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar")))
    )

    add(ConfigurationType.IMPLEMENTATION, Libs.Kotlin.kotlinStdlib)
    Libs.Support.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }

    Libs.Lifecycle.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }

    Libs.Room.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }
    Libs.Room.kapt.forEach { add(ConfigurationType.KAPT, it) }

    Libs.Navigation.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }

    Libs.Networking.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }
    Libs.Networking.testImplementation.forEach { add(ConfigurationType.TEST_IMPLEMENTATION, it) }

    Libs.DaggerHilt.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }
    Libs.DaggerHilt.kapt.forEach { add(ConfigurationType.KAPT, it) }
    Libs.DaggerHilt.testImplementation.forEach { add(ConfigurationType.TEST_IMPLEMENTATION, it) }
    Libs.DaggerHilt.kaptTest.forEach { add(ConfigurationType.KAPT_TEST, it) }
    Libs.DaggerHilt.androidTestImplementation.forEach { add(ConfigurationType.ANDROID_TEST_IMPLEMENTATION, it) }
    Libs.DaggerHilt.kaptAndroidTest.forEach { add(ConfigurationType.KAPT_ANDROID_TEST, it) }

    Libs.Coroutines.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }
    Libs.Coroutines.testImplementation.forEach { add(ConfigurationType.TEST_IMPLEMENTATION, it) }

    Libs.UnitTest.testImplementation.forEach { add(ConfigurationType.TEST_IMPLEMENTATION, it) }
    Libs.InstrumentTest.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }
    Libs.InstrumentTest.androidTestImplementation.forEach { add(ConfigurationType.ANDROID_TEST_IMPLEMENTATION, it) }

    Libs.Glide.implementation.forEach { add(ConfigurationType.IMPLEMENTATION, it) }
    Libs.Glide.kapt.forEach { add(ConfigurationType.KAPT, it) }

    add(ConfigurationType.IMPLEMENTATION, Libs.ThirdParty.timber)
    add(ConfigurationType.IMPLEMENTATION, Libs.ThirdParty.pageIndicatorView)
}
