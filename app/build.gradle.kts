plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.githubexplorerkotlin"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.githubexplorerkotlin"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit core library
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson converter for JSON serialization/deserialization
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    val room_version = "2.6.1" // Use the latest stable version

    implementation ("androidx.room:room-runtime:$room_version")
    implementation( "androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    val lifecycle_version = "2.10.0"
    val arch_version = "2.2.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")

    // Moshi Kotlin extensions for data classes and sealed classes
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2") // Check for the latest version
    // If using Kapt:
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")
    implementation("com.github.bumptech.glide:glide:5.0.5")
}