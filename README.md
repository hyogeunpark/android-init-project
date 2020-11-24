# initial-project-settings

[![](https://jitpack.io/v/hyogeunpark/android-init-project.svg)](https://jitpack.io/#hyogeunpark/android-init-project)

## Add Android Studio Gradle

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Step 2. Add the dependency
```gradle
dependencies {
  implementation 'com.github.hyogeunpark:android-init-project:0.3'
}
```
