plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.hibernate.orm") version "6.2.0.CR3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lab2:dal"))

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    testImplementation("org.mockito:mockito-core:3.9.0")
    testImplementation("org.mockito:mockito-junit-jupiter:3.9.0")

    implementation("joda-time:joda-time:2.3")
    implementation("org.postgresql:postgresql:42.2.16")
}

tasks.test {
    useJUnitPlatform()
}