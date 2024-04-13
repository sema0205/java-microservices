plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.springframework.boot") version "3.2.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lab2:service"))
    implementation(project(":lab2:domain"))
    implementation("joda-time:joda-time:2.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("junit:junit:4.13.1")

    implementation("org.springframework.boot:spring-boot-test:3.2.2")
    implementation("org.springframework.boot:spring-boot-test-autoconfigure:3.2.2")
    implementation("org.springframework:spring-test:6.1.3")

    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    implementation("org.springframework:spring-web:6.1.5")

    implementation("org.springframework.security:spring-security-core:6.2.3")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.4.0")
    implementation("org.hibernate:hibernate-core:6.2.6.Final")
    implementation("org.springframework.boot:spring-boot-starter-graphql:2.7.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
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