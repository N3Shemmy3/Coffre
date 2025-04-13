// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}
buildscript {
    val objectboxVersion by extra("4.2.0") // For KTS build scripts

    repositories {
        mavenCentral()
    }

    dependencies {
        // Android Gradle Plugin 8.0 or later supported
        classpath(libs.gradle)
        classpath("io.objectbox:objectbox-gradle-plugin:$objectboxVersion")
    }
}