plugins {
	java
	jacoco
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	id ("org.sonarqube") version "6.1.0.5360"
	id ("org.liquibase.gradle") version "2.2.0"
}

group = "dev.sheet-co"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-logging")
	implementation("org.liquibase:liquibase-core")
	implementation("org.instancio:instancio-core:5.4.1")
    implementation("org.mapstruct:mapstruct:1.6.3")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.junit.jupiter:junit-jupiter")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter")
	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")

	testImplementation("org.mockito:mockito-core:5.17.0")
	testImplementation("net.datafaker:datafaker:2.4.3")
	testImplementation("io.rest-assured:rest-assured:5.5.1")
	testImplementation ("io.rest-assured:json-schema-validator:5.5.1")

	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
}

tasks.withType<Test> {
	useJUnitPlatform()

}

tasks.jacocoTestReport {
	reports { xml.required.set(true) }
}

sonar {
	properties {
		property("sonar.projectKey", "sheet-co_request-races")
		property("sonar.organization", "sheet-co")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}




