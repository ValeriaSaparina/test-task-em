plugins {
    id("kotlin-kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("plugin.serialization") version "1.9.22"
}

android {
    namespace = "com.example.testtask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testtask"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":core:base"))
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:navigation"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature:coverLetter"))
    implementation(project(":feature:favoriteVacancies"))
    implementation(project(":feature:login"))
    implementation(project(":feature:login2"))
    implementation(project(":feature:main"))
    implementation(project(":feature:relevantVacancies"))
    implementation(project(":feature:respond"))
    implementation(project(":feature:vacancyDetails"))
    implementation(project(":presentation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}