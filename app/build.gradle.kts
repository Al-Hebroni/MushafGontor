plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.mushaf.gontor"
    compileSdk = 35 // <--- PERUBAHAN DI SINI (dari 34 ke 35)

    defaultConfig {
        applicationId = "com.mushaf.gontor"
        minSdk = 24
        targetSdk = 35 // <--- PERUBAHAN DI SINI (dari 34 ke 35)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Kita tidak perlu multidex jika tidak ada library lama
        // multiDexEnabled = true
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Library untuk Coroutines (kita mungkin tidak membutuhkannya lagi, tapi tidak apa-apa)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Library untuk Gson (PENTING untuk membaca data.json)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // implementation("com.squareup.retrofit2:retrofit:2.9.0") // Tidak perlu retrofit
    // implementation("com.squareup.okhttp3:logging-interceptor:4.11.0") // Tidak perlu interceptor

    // --- LIBRARY PDF VIEWER KITA HAPUS ---
    // implementation("com.github.barteksc:android-pdf-viewer:2.8.2") {
    //     exclude(group = "com.android.support", module = "support-compat")
    // }

    // --- MENGGUNAKAN LIBRARY BARU YANG ANDA TEMUKAN ---
    implementation("io.github.afreakyelf:Pdf-Viewer:2.3.7")
}