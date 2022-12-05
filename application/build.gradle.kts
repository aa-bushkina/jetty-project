dependencies {
    implementation(project(":jooq-generated"))
    implementation("org.jboss.resteasy:resteasy-guice:4.5.8.Final")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider:4.5.8.Final")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("commons-io:commons-io:2.11.0")
}
