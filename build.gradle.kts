import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.8"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.graalvm.buildtools.native") version "0.9.28"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "dev.rumblekat"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.4"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-function-webflux")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-function-context")
    implementation("org.springframework.cloud:spring-cloud-starter-function-web")
    implementation("org.springframework.cloud:spring-cloud-function-adapter-aws:4.0.1")
    implementation("com.amazonaws:aws-lambda-java-events:3.11.1")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    runtimeOnly("com.amazonaws:aws-lambda-java-log4j2:1.5.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("demo-function.jar")
    dependencies {
        exclude("org.springframework.cloud:spring-cloud-function-web")
    }
    mergeServiceFiles()
    append("META-INF/spring.handlers")
    append("META-INF/spring.schemas")
    append("META-INF/spring.tooling")
    transform(com.github.jengelman.gradle.plugins.shadow.transformers.PropertiesFileTransformer::class.java) {
        paths.add("META-INF/spring.factories")
        mergeStrategy = "append"
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
    environment.put("BP_NATIVE_IMAGE", "true")
}

graalvmNative {
    toolchainDetection.set(false)
}
