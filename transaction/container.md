### Build

- **Base image**: `gradle:jdk21` (build) and `openjdk:21` (final)
- **Workdir**: `/app
- **Files**:
    - `build.gradle.kts` & `settings.gradle.kts`: Gradle's configuration files
    - `gradle.properties`: Gradle's properties file
    - `src`: Source code
    - `gradlew` & `gradlew.bat`: Gradle's wrapper scripts
    - `gradle`: Gradle's folder
- **Jar Artifact**: `./gradlew bootJar`
- **Ports**: '8000'