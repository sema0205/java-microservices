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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation(project(":lab2:dal"))
    implementation(project(":lab2:web"))
    implementation(project(":lab2:service"))
    implementation(project(":lab2:domain"))

    implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")

    implementation("joda-time:joda-time:2.3")

    implementation("org.hibernate:hibernate-core:5.4.30.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.2.3.Final")
    implementation("javax.xml.bind:jaxb-api:2.3.0")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")

    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    implementation("org.springframework:spring-web:6.1.5")

    implementation("org.springframework.security:spring-security-core:6.2.3")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.4.0")

    implementation("org.springframework:spring-tx:6.1.3")

    testCompileOnly("org.mockito:mockito-all:1.9.5")
    testCompileOnly("org.mockito:mockito-junit-jupiter:5.9.0")
}

tasks.test {
    useJUnitPlatform()
}