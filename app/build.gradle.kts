plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "dev.n3shemmy3.coffre"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.n3shemmy3.coffre"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
    implementation(libs.fragment)
    implementation(libs.viewpager2)
    implementation(libs.viewmodel)
    implementation(libs.viewmodel.savedstate)
    implementation(libs.livedata)
    implementation(libs.recyclerview)
    implementation(libs.recyclerview.selection)
    implementation(libs.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}