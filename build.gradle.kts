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

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly(("org.postgresql:postgresql"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.mockito:mockito-core")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter")
	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")

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

//liquibase {
//	activities {
//		register("main") {
//			var changeLog = "src/main/resources/db/changelog/db.changelog-master.yaml"
//			var url = "jdbc:postgresql://localhost:5432/race_base"
//			var username = "racer"
//			var password = "1234"
//			var driver = "org.postgresql.Driver"
//		}
//	}
//	runList = "main"
//}

//liquibase {
//	activities {
//		register("main") {
//			var changeLog = "src/main/resources/db/changelog/db.changelog-master.yaml"
//			var url = "jdbc:h2:mem:project"
//			var username = "sa"
//			var password = ""
//			var driver = "org.h2.Driver"
//		}
//	}
//	runList = "main"
//}


