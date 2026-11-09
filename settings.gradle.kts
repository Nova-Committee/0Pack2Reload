pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/") {
            content { includeGroupByRegex("net\\.fabricmc(\\..*)?") }
        }
        maven("https://maven.neoforged.net/releases/") {
            content { includeGroupByRegex("net\\.neoforged(\\..*)?") }
        }
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.8"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "0Pack2Reload"

stonecutter {
    create(rootProject) {
        mapBuilds { _, target ->
            when {
                target.project.endsWith("-neoforge") -> "build.neoforge.gradle"
                target.version == "1.21.11" -> "build.fabric-remap.gradle"
                else -> "build.fabric.gradle"
            }
        }

        for (minecraft in listOf("1.21.11", "26.1.2", "26.2", "26.3")) {
            for (loader in listOf("fabric", "neoforge")) {
                version("$minecraft-$loader", minecraft)
            }
        }
        vcsVersion = "26.3-fabric"
    }
}
