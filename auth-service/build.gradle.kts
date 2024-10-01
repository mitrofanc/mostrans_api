import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// import ru.itech.sno_api.gradle.Versions

object Versions {
    const val ROOT_PROJECT_VERSION = "0.0.1-SNAPSHOT"
    const val SPRING_DOC_VERSION = "2.1.0"
    const val JJWT_API_VERSION = "0.12.3"
    const val JVM_VERSION = "17"
    const val MOCKITO_VERSION = "2.2.0"
}

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.itech"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

extra["springCloudVersion"] = "2023.0.0"

dependencies {
    Versions.apply {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("io.jsonwebtoken:jjwt-api:$JJWT_API_VERSION")
        implementation("io.jsonwebtoken:jjwt-impl:$JJWT_API_VERSION")
        implementation("io.jsonwebtoken:jjwt-jackson:$JJWT_API_VERSION")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springdoc:springdoc-openapi-starter-common:$SPRING_DOC_VERSION")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$SPRING_DOC_VERSION")
        // implementation("mysql:mysql-connector-java:8.0.33")
        implementation("org.postgresql:postgresql:42.2.8")
        implementation("org.springframework.cloud:spring-cloud-config-server")
        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
        // implementation("com.github.loki4j:loki-logback-appender:1.4.1")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$MOCKITO_VERSION")
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}