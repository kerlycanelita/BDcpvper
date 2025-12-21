```markdown
# BDcpvper Plugin

Plugin para PaperMC 1.21+ que permite a jugadores Bedrock usar Cristales del End y Respawn Anchors sin delay.

## 🎮 Funcionalidad

- Detecta jugadores Bedrock vía Floodgate
- Cristales del End: colocación y destrucción instantánea
- Respawn Anchors: colocación, destrucción y drop sin delay
- Solo afecta a jugadores Bedrock; Java mantiene su comportamiento

## 📋 Requisitos

- Java 21+
- PaperMC 1.21.1+ (compatible con Spigot)
- Geyser + Floodgate instalados

## 🔧 Instalación

1. Instala Geyser y Floodgate
2. Compila:
   ```bash
   ./gradlew build
   ```
3. Copia `build/libs/BDcpvper-1.0.jar` a `plugins/`
4. Reinicia el servidor

## 📦 Descargas

- [Geyser](https://geysermc.org/download)
- [Floodgate](https://geysermc.org/download)

## 🛠️ Compilación

```bash
# Windows
gradlew.bat build

# Linux/Mac
./gradlew build
```

## 🔍 Verificación

Al activarse correctamente, verás en consola:

```
[BDcpvper] BDcpvper activado correctamente!
[BDcpvper] Jugadores Bedrock pueden usar:
[BDcpvper] - Cristales del End sin delay
[BDcpvper] - Respawn Anchors sin delay
```

Si Floodgate no está instalado:

```
[BDcpvper] FLOODGATE NO ENCONTRADO!
[BDcpvper] Este plugin requiere Floodgate para funcionar
```

## 🐛 Problemas comunes

- Verifica que Floodgate y Geyser estén instalados y funcionando
- Usa Paper/Spigot 1.21.1+
- Revisa los logs si el plugin no se activa

## 👤 Autor

Creado por **Zymekoh**

## 🤝 Contribuciones

¡Bienvenidas! Puedes abrir issues o pull requests.
```