plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.miprimeraapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.miprimeraapp"
        minSdk = 23
        targetSdk = 36
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // 1. El Firebase BoM (maneja las versiones automáticamente)
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    // 2. Las librerías de Firebase (ESTAS NO LLEVAN PLATFORM)
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-storage")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.activity:activity:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    // 3. Firebase UI para Storage
    implementation("com.firebaseui:firebase-ui-storage:8.0.0")

    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")
}