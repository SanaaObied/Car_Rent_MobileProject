// build.gradle.kts
import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.application") version "7.0.0"
}

android {
    compileSdk = 34
    buildToolsVersion = "30.0.3" // Adjust the version as needed

    defaultConfig {
        applicationId = "com.example.activity1" // Corrected 'namespace' to 'applicationId'
        minSdk = 24
        targetSdk = 30 // Set the targetSdk version here
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
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0") // Use annotationProcessor for Glide's compiler
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}


