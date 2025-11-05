plugins {
    id("java")
    id("application")
}

repositories {
    mavenCentral()
}

// JUnit 5 (per i test)
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    // Nota: questo main NON esisterà. Eseguiremo solo i test.
    mainClass.set("it.unibo.warehouse.TestWarehouse")
}

// Configura Gradle per usare JUnit 5
tasks.withType<Test> {
    useJUnitPlatform()
}