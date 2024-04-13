plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lab2:domain"))
    implementation(project(":lab2:service"))
    implementation(project(":lab2:web"))
    implementation(project(":lab2:dal"))

    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-graphql:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-mail:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-freemarker:3.2.2")

    implementation("org.springframework.boot:spring-boot-test:3.2.2")
    implementation("org.springframework.boot:spring-boot-test-autoconfigure:3.2.2")
    implementation("org.springframework:spring-test:6.1.3")

    implementation("junit:junit:4.13.1")

    runtimeOnly("org.postgresql:postgresql:42.7.1")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")

    implementation("org.liquibase:liquibase-core:4.26.0")
    implementation("net.lbruun.springboot:preliquibase-spring-boot-starter:1.4.0")
    implementation("com.graphql-java-kickstart:graphql-java-kickstart:15.2.0")
    implementation("com.graphql-java:graphql-java-extended-scalars:20.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.springframework:spring-web:6.1.5")
    implementation("org.springframework.security:spring-security-core:6.2.3")
    implementation("org.springframework:spring-tx:6.1.3")

    testImplementation("com.h2database:h2:1.4.200")
    implementation("org.postgresql:postgresql:42.2.16")
}

tasks {
    getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        isEnabled = false
    }

    getByName<org.gradle.jvm.tasks.Jar>("jar") {
        enabled = true
    }
}

