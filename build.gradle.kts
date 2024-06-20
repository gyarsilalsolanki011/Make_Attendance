buildscript {
    dependencies {
        classpath(libs.google.services)
    }

    //Sha1 Key 72:4e:65:91:db:a1:d4:e8:09:7c:4d:d3:30:d6:b3:36:72:9f:0d:ed
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
}