plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.objectbox)
}

android {
    namespace = "dev.n3shemmy3.coffre"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.n3shemmy3.coffre"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0 Amanatsu-Demo"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildToolsVersion = "35.0.0"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
    implementation(libs.fragment)
    implementation(libs.glide)
    implementation (libs.gson)
    implementation(libs.eventbus)
    implementation(libs.viewpager2)
    implementation(libs.viewmodel)
    implementation(libs.viewmodel.savedstate)
    implementation(libs.livedata)
    implementation(libs.splashscreen)
    implementation(libs.recyclerview)
    implementation(libs.recyclerview.selection)
    implementation(libs.preference)
    implementation(libs.paging)
    implementation (libs.iconics.core)
    implementation(libs.google.material.typeface.outlined){ artifact { type = "aar" } }
    implementation (libs.ticker)
    implementation (libs.objectbox.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}