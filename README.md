# BDcpvper Plugin

Plugin para PaperMC 1.21+ que permite a jugadores de Bedrock Edition usar cristales del End y Respawn Anchors sin delay.

## 🎮 Características

- ✅ Detección automática de jugadores Bedrock usando Floodgate
- ✅ Cristales del End:
  - Colocación instantánea sin restricciones
  - Destrucción instantánea al golpearlos
  - Explosión generada correctamente
- ✅ Respawn Anchors:
  - Colocación sin delay
  - Destrucción instantánea
  - Drop automático del item

## 📋 Requisitos

- **Java 21** o superior
- **PaperMC 1.21.1** o superior (también compatible con Spigot)
- **Geyser** instalado en el servidor
- **Floodgate** instalado en el servidor

## 🔧 Instalación

1. Asegúrate de tener Geyser y Floodgate instalados en tu servidor
2. Compila el plugin:
   ```bash
   ./gradlew build
   ```
3. Copia el archivo JAR generado de `build/libs/BDcpvper-1.0.jar` a la carpeta `plugins/` de tu servidor
4. Reinicia el servidor

## 📦 Descarga de dependencias

- [Geyser](https://geysermc.org/download)
- [Floodgate](https://geysermc.org/download)

## 🛠️ Compilación

### Windows
```bash
gradlew.bat build
```

### Linux/Mac
```bash
./gradlew build
```

El archivo JAR se generará en `build/libs/`

## 📖 Uso

El plugin funciona automáticamente. Los jugadores de Bedrock Edition podrán:

1. **Cristales del End**: Colocarlos y destruirlos sin el delay típico de Bedrock
2. **Respawn Anchors**: Colocarlos y romperlos instantáneamente

Los jugadores de Java Edition no se ven afectados y mantienen el comportamiento normal.

## 🔍 Verificación

Cuando el plugin se active correctamente, verás en la consola:

```
[BDcpvper] ============================================
[BDcpvper] BDcpvper activado correctamente!
[BDcpvper] Jugadores Bedrock pueden usar:
[BDcpvper] - Cristales del End sin delay
[BDcpvper] - Respawn Anchors sin delay
[BDcpvper] ============================================
```

Si Floodgate no está instalado, verás:

```
[BDcpvper] ============================================
[BDcpvper] FLOODGATE NO ENCONTRADO!
[BDcpvper] Este plugin requiere Floodgate para funcionar
[BDcpvper] Descarga: https://geysermc.org/download
[BDcpvper] ============================================
```

## 🐛 Problemas comunes

### El plugin no se activa
- Verifica que Floodgate esté instalado correctamente
- Revisa que estés usando Paper/Spigot 1.21.1 o superior
- Verifica los logs del servidor para más detalles

### Los jugadores Bedrock no tienen funcionalidad
- Asegúrate de que Geyser esté funcionando correctamente
- Verifica que Floodgate pueda detectar a los jugadores Bedrock
- Revisa los permisos (por defecto todos tienen acceso)

## 📝 Licencia

Este plugin es de código abierto y puede ser modificado según tus necesidades.

## 👤 Autor

Creado por **Zymekoh**

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Siéntete libre de abrir issues o pull requests.
