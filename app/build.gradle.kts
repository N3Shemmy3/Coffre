plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "dev.n3shemmy3.coffre"
    compileSdk = 36
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "dev.n3shemmy3.coffre"
        minSdk = 26
        targetSdk = 36
        versionCode = 4
        versionName = "1.0.3 Amanatsu"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("debug")
    }
    signingConfigs {
        create("release") {
            val storePath = project.findProperty("SHEMMY_RELEASE_STORE_FILE")?.toString()
            if (storePath == null) {
                println("Warning: SHEMMY_RELEASE_STORE_FILE is not defined.")
            }

            storeFile = storePath?.let { file(it) }
            storePassword = project.findProperty("SHEMMY_RELEASE_STORE_PASSWORD") as String?
            keyAlias = project.findProperty("SHEMMY_RELEASE_KEY_ALIAS") as String?
            keyPassword = project.findProperty("SHEMMY_RELEASE_KEY_PASSWORD") as String?
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    /* AndroidX */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation)


    /* Compose */
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.icons.extended)

    /* Chart Library */
    implementation(libs.vico.compose)
    implementation(libs.vico.compose.m3)

    /* Kotlin */
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    /* Testing */
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}