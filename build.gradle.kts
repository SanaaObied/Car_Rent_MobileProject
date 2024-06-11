// Top-level build file where you can add configuration options common to all sub-projects/modules.
// File: build.gradle.kts
plugins {
    id("com.android.application")
}

repositories {
    google()
    mavenCentral()
}

android {
    namespace = "com.example.Activity1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.Activity1"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.github.bumptech.glide:glide:4.12.0")
}
