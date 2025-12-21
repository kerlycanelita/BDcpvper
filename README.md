BDcpvper Plugin

Plugin para PaperMC 1.21+ que permite a jugadores Bedrock usar End Crystals y Respawn Anchors sin delay, manteniendo el comportamiento normal para jugadores Java.

Características
- Detecta jugadores Bedrock mediante Floodgate
- End Crystals: colocación y destrucción instantánea
- Respawn Anchors: colocación, destrucción y drop sin delay
- Solo afecta a Bedrock; Java no se ve alterado

Requisitos
- Java 21+
- PaperMC 1.21.1+ (compatible con Spigot)
- Geyser y Floodgate instalados

Instalación
Opción 1: Descargar Release
1. Descarga el archivo .jar desde la sección Releases del repositorio
2. Copia el archivo en la carpeta plugins
3. Reinicia el servidor

Opción 2: Compilar manualmente
Windows:
gradlew.bat build

Linux / macOS:
./gradlew build

Luego copia build/libs/BDcpvper-1.0.jar a la carpeta plugins y reinicia el servidor

Dependencias
- https://geysermc.org/download

Verificación
Al iniciar el servidor, la consola mostrará:
[BDcpvper] BDcpvper activado correctamente!
[BDcpvper] Jugadores Bedrock pueden usar:
[BDcpvper] - End Crystals sin delay
[BDcpvper] - Respawn Anchors sin delay

Autor
Zymekoh

Contribuciones
Se aceptan issues y pull requests
