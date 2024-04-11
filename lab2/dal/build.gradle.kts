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
    implementation(project(":lab2:domain"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("joda-time:joda-time:2.3")
    implementation("org.postgresql:postgresql:42.2.16")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("jakarta.persistence:jakarta.persistence-api:3.0.0")
    implementation("org.hibernate:hibernate-core:5.5.0.Final")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    implementation("org.springframework:spring-web:6.1.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
    implementation("org.springframework.data:spring-data-jpa:3.2.4")
}

tasks.test {
    useJUnitPlatform()
}
