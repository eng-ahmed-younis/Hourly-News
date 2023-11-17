plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.florinda.hourlynews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.florinda.hourlynews"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            // Enables code shrinking, obfuscation, and optimization for only
            isMinifyEnabled = true
            // Enables resource shrinking, which is performed by the

             isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
    }


}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(project(mapOf("path" to ":data")))


    // material compose
    implementation("androidx.compose.material3:material3:1.1.2")

    /** Lifecycle */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    /** Material icons */
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    /** Navigation */
    val nav_version = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    /** Dagger hilt */
    val dagger_version = "2.48.1"
    implementation("com.google.dagger:hilt-android:$dagger_version")
    //dagger-hilt
    val hilt_compiler_version = "1.1.0"
    implementation("androidx.hilt:hilt-navigation-compose:$hilt_compiler_version")
    kapt("com.google.dagger:hilt-compiler:$dagger_version")
    kapt("androidx.hilt:hilt-compiler:$hilt_compiler_version")

    /** Retrofit */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    /** OkHttp */
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    /** Coil */
    implementation("io.coil-kt:coil-compose:2.5.0")


    // system bars customization
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
}