plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testCompileOnly("org.mockito:mockito-all:1.9.5")
    testCompileOnly("org.mockito:mockito-junit-jupiter:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}