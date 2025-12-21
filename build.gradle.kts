plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.zymekoh"
version = "1.1-ULTRA-FAST"

repositories {
    mavenCentral()
    
    // Repositorio de Spigot/Paper
    maven("https://repo.papermc.io/repository/maven-public/")
    
    // Repositorio de Floodgate/Geyser
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://repo.opencollab.dev/maven-releases/")
}

dependencies {
    // Paper API
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    
    // Floodgate API
    compileOnly("org.geysermc.floodgate:api:2.2.3-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.shadowJar {
    archiveFileName.set("${project.name}-${project.version}.jar")
    destinationDirectory.set(file("build/libs"))
    
    // No incluir Floodgate en el JAR final (viene del servidor)
    dependencies {
        exclude(dependency("org.geysermc.floodgate:api"))
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Xlint:unchecked")
    options.compilerArgs.add("-Xlint:deprecation")
}
