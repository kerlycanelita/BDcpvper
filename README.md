# BDcpvper

BDcpvper is a PaperMC 1.21+ plugin that improves Crystal PvP interaction for Bedrock players detected through Floodgate, while keeping Java players on normal behavior.

## Features
- Detects Bedrock players on join.
- Fast End Crystal handling for Bedrock players.
- Fast Respawn Anchor handling for Bedrock players.
- Configurable logs, welcome messages, and PvP behavior.
- Respects events already cancelled by other plugins (claims, region protection, etc.).

## Requirements
- Java 21 or newer.
- PaperMC 1.21.1+ (or compatible fork).
- Geyser + Floodgate installed on the server.

## Build (Compile)
Linux/macOS:

```bash
./gradlew clean build
```

Windows:

```bat
gradlew.bat clean build
```

Generated jar:

```text
build/libs/BDcpvper-<version>.jar
```

## Installation
1. Build the plugin.
2. Copy the jar from `build/libs/` into your server `plugins/` folder.
3. Start or restart the server.

## Configuration
Main config file:

```text
plugins/BDcpvper/config.yml
```

Main sections:
- `logs.*`: console logs for join and block/crystal actions.
- `messages.*`: optional Bedrock welcome messages.
- `crystals.*`: crystal placement, breaking, and explosion behavior.
- `anchors.*`: anchor placement/breaking, item drop, and experience.

## Common Errors and Fixes
`FLOODGATE NOT FOUND!`
- Cause: Floodgate is missing or not loading.
- Fix: Install/enable Floodgate (and Geyser if needed), then restart the server.

`UnsupportedClassVersionError` or Java version errors
- Cause: Server/runtime is using Java older than 21.
- Fix: Run server and build with Java 21+.

`Could not find or load main class org.gradle.wrapper.GradleWrapperMain`
- Cause: Gradle wrapper files are missing/corrupted.
- Fix: Restore `gradle/wrapper/*`, `gradlew`, and `gradlew.bat` from the repository.

Plugin does not appear in `/plugins`
- Cause: Build failed, wrong jar copied, or startup errors in console.
- Fix: Rebuild with `clean build`, copy the newest jar from `build/libs`, and check startup logs.

## Quick Validation
After startup, the console should show:
- `BDcpvper enabled successfully`

If `logs.player-join: true`, joining with a Bedrock account should also print a Bedrock detection log.

## Author
Zymekoh
