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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation(project(":lab2:dal"))
    implementation(project(":lab2:controller"))
    implementation(project(":lab2:service"))

    implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")

    implementation("joda-time:joda-time:2.3")

    implementation("org.hibernate:hibernate-core:5.4.30.Final")

    testCompileOnly("org.mockito:mockito-all:1.9.5")
    testCompileOnly("org.mockito:mockito-junit-jupiter:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}