plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.hibernate.orm") version "6.2.0.CR3"
    id("org.springframework.boot") version "3.2.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lab2:dal"))
    implementation(project(":lab2:domain"))

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    testImplementation("org.mockito:mockito-core:3.9.0")
    testImplementation("org.mockito:mockito-junit-jupiter:3.9.0")

    implementation("joda-time:joda-time:2.3")
    implementation("org.postgresql:postgresql:42.2.16")
    implementation("org.springframework.data:spring-data-jpa:3.2.4")

    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.4"))
    implementation("org.springframework.boot:spring-boot-starter-security")


    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        isEnabled = false
    }

    getByName<org.gradle.jvm.tasks.Jar>("jar") {
        enabled = true
    }
}