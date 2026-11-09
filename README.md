# 0Pack2Reload

0Pack2Reload prevents an unnecessary reload when you close the resource pack screen without changing the selection. This can happen on first use when Minecraft has loaded its default packs but has yet to save the pack list.

## Installation

Place the JAR for your Minecraft version and loader in the client's `mods` folder.

## Building from source

With JDK 25, run `sh ./gradlew buildAndCollect` to build all eight targets. Gradle can download JDK 21 for the 1.21.11 targets if needed. The mod JARs go to `build/libs/`. On Windows, use `gradlew.bat buildAndCollect`.

To build one target, use its version and loader as the Gradle project name, for example `sh ./gradlew :26.3-neoforge:buildAndCollect`. Use `:26.3-neoforge:runClient` to start that target's development client.
