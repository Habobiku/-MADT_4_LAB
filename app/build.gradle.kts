plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.madt_4_lab"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.madt_4_lab"
        minSdk = 23
        targetSdk = 33
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("org.mockito:mockito-android:3.12.4")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.21")
    testImplementation ("org.mockito:mockito-core:3.12.4")
    androidTestImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.21")
    androidTestImplementation ("androidx.test:core-ktx:1.4.0")
    testImplementation ("androidx.test:core-ktx:1.4.0")
    androidTestImplementation ("com.android.support.test:rules:1.0.2")
    testImplementation ("com.android.support.test:rules:1.0.2")
}
