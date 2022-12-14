plugins {
    java
    `kotlin-dsl`
    application
    id("nu.studer.jooq") version "8.0" apply false
}
allprojects {
    group = "ru.vk"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("nu.studer.jooq")
    }

    dependencies {
        implementation("org.eclipse.jetty:jetty-servlet:9.4.33.v20201020")
        implementation("org.eclipse.jetty:jetty-server:9.4.33.v20201020")
        implementation("org.eclipse.jetty:jetty-servlets:9.4.33.v20201020")

        implementation("org.flywaydb:flyway-core:9.8.3")
        implementation("org.postgresql:postgresql:42.5.1")

        implementation("org.jooq:jooq:3.17.5")
        implementation("org.jooq:jooq-codegen:3.17.5")
        implementation("org.jooq:jooq-meta:3.17.5")

        implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
        implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")

        implementation("com.google.inject.extensions:guice-servlet:5.1.0")
        implementation("org.glassfish.hk2:guice-bridge:2.6.0")

        implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
        implementation("javax.servlet:javax.servlet-api:4.0.1")

        implementation("org.jetbrains:annotations:23.0.0")
        implementation("com.google.inject:guice:5.1.0")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}