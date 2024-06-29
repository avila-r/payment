plugins {
	/**
	 * Spring Boot plugins for application management
	 */
		id("org.springframework.boot") version "3.3.1"
		id("io.spring.dependency-management") version "1.1.5"

	/**
	 * Kotlin's plugins
	 */
		// JPA support for generating no-args constructor at compiled bytecode
		kotlin("plugin.jpa") version "1.9.24"

		// JVM support
		kotlin("jvm") version "1.9.24"

		// Spring support
		kotlin("plugin.spring") version "1.9.24"
}

group = "com.avila"
version = "0.1"

java {
	toolchain {
		// Set Java language version to 21
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	// Use Maven Central repository for dependencies
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.2"

/**
 * Dependencies Configuration
 */
dependencies {

	/**
	 * Utils
	 */
		// Library for handling result types
		implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")

	/**
	 * Spring Boot starters
	 */
		// Spring Actuator:
		implementation("org.springframework.boot:spring-boot-starter-actuator")
		// Spring Data JPA:
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		// Spring Web:
		implementation("org.springframework.boot:spring-boot-starter-web")
		// Spring Cloud Config Client:
		// implementation("org.springframework.cloud:spring-cloud-starter-config")
		// Spring for RabbitMQ:
		implementation("org.springframework.boot:spring-boot-starter-amqp")

	/**
	 * Jackson
	 */
		// Module for Kotlin's support
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	/**
	 * Misc
	 */
		// Reflect support
		implementation("org.jetbrains.kotlin:kotlin-reflect")

	/**
	 * Datasource
	 */
		// PostgreSQL
		runtimeOnly("org.postgresql:postgresql")

	/**
	 * Docs
	 */
		// Springdoc OpenAPI for API auto-generated documentation
		implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	/**
	 * Tests
	 */
		// Spring Boot-related tests, including application context and other features
		testImplementation("org.springframework.boot:spring-boot-starter-test")

		// JUnit support
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")

		// AssertJ for improving assertions
		testImplementation("org.assertj:assertj-core")

		// Mockito for mocking dependencies
		testImplementation("org.mockito:mockito-core")

		// H2 as testing-purpose datasource
		testImplementation("com.h2database:h2")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

/**
 * Kotlin Compiler Configuration
 */
kotlin {
	compilerOptions {
		// Enable strict JSR-305 annotations
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

/**
 * Test Tasks Configuration
 */
tasks.withType<Test> {
	// Use JUnit 5 platform for testing
	useJUnitPlatform()
}
