import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("kapt") version "1.3.72"
}

group = "com.ilarahealth"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
project.version = "4.1.0"

repositories {
	mavenCentral()
	mavenLocal()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("ca.uhn.hapi.fhir:hapi-fhir-base:${project.version}")
	implementation("ca.uhn.hapi.fhir:hapi-fhir-client:${project.version}")
	implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-r4:${project.version}")
	implementation("org.mapstruct:mapstruct:1.3.1.Final")

	kapt("org.mapstruct:mapstruct-processor:1.3.1.Final")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
