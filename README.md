# Kmm Version Catalog
![Version](https://img.shields.io/maven-central/v/io.github.raghavendra-dg/version-catalog-kmm?style=flat-square)

## Usage

You need to add version catalog in `settings.gradle.kts` from remote repository:

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from("io.github.raghavendra-dg:version-catalog-kmm:1.0.0")
        }
    }
}
```
After the sync gradle create accessors from dependencies like:

```kotlin
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}
```
