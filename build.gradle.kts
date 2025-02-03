// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id ("com.google.dagger.hilt.android")  version "2.51.1" apply  false //hilt
}

// In case the Kotlin version is not defined, you can specify it explicitly here:
buildscript {
    val kotlin_version by extra("2.0.0") // Or the latest stable version

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}