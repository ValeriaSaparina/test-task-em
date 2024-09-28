plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.vacancydetails"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        viewBinding = true
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

    implementation(project(":presentation"))
    implementation(project(":core:di"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:base"))
    implementation(project(":core:common"))

    // viewBindingDelegate
    implementation(libs.viewbindingpropertydelegate.noreflection)

    // mvvm
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Dagger
    implementation(libs.dagger)
    "kapt"(libs.dagger.compiler)

    // adapterDelegate
    implementation(libs.adapterdelegates4.kotlin.dsl)
    implementation(libs.adapterdelegates4.kotlin.dsl.viewbinding)

    implementation(libs.androidx.navigation.fragment.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}