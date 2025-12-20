# BDcpvper

⚔️ **Plugin PvP para PaperMC que elimina el delay de cristales y anchors para jugadores Bedrock**

Un plugin ligero y eficiente que detecta jugadores de Bedrock Edition (usando Floodgate) y les permite usar cristales del End y Respawn Anchors sin el delay característico de la plataforma, igualando las condiciones de PvP con jugadores de Java Edition.

---

## 🎯 Características

- ✅ **Detección automática** de jugadores Bedrock mediante Floodgate API
- ⚡ **Colocación instantánea** de cristales del End y Respawn Anchors
- 💥 **Destrucción sin delay** con explosiones y drops correctos
- 🎮 **No afecta a jugadores Java** - mantiene el comportamiento vanilla
- 🪶 **Ligero y optimizado** - mínimo impacto en el rendimiento
- 🔧 **Plug & Play** - no requiere configuración adicional

---

## 📋 Requisitos

- **Minecraft**: 1.21+
- **Servidor**: PaperMC / Spigot 1.21.1+
- **Java**: 21 o superior
- **Dependencias**: 
  - [Geyser](https://geysermc.org/) (para crossplay Bedrock/Java)
  - [Floodgate](https://geysermc.org/) (para detección de jugadores Bedrock)

---

## 🚀 Instalación

1. Asegúrate de tener **Geyser** y **Floodgate** instalados en tu servidor
2. Descarga la última versión de [Releases](../../releases)
3. Coloca `BDcpvper-1.0.jar` en la carpeta `plugins/` de tu servidor
4. Reinicia el servidor
5. ¡Listo! El plugin funcionará automáticamente

---

## 🎮 ¿Cómo funciona?

### Para jugadores Bedrock:
- Los **cristales del End** se colocan y rompen instantáneamente
- Los **Respawn Anchors** se colocan y destruyen sin delay
- Las explosiones funcionan correctamente
- Items dropean normalmente

### Para jugadores Java:
- Sin cambios - comportamiento vanilla de Minecraft

---

## 🛠️ Compilación desde código fuente

```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/BDcpvper.git
cd BDcpvper

# Compilar con Gradle
./gradlew build

# El JAR estará en: build/libs/BDcpvper-1.0.jar
```

---

## 📖 API y Dependencias

- **Paper API** 1.21.1
- **Floodgate API** 2.2.3
- **Java** 21

---

## ⚙️ Configuración

El plugin funciona **out-of-the-box** sin necesidad de configuración. Sin embargo, puedes modificar `config.yml` para personalizar:

```yaml
crystals:
  instant-place: true
  instant-break: true
  explosion-power: 6.0

anchors:
  instant-place: true
  instant-break: true
```

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/NuevaCaracteristica`)
3. Commit tus cambios (`git commit -m 'Agrega nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

---

## 🐛 Reportar bugs

Si encuentras algún bug, por favor [abre un issue](../../issues) con:
- Versión del servidor (Paper/Spigot)
- Versión de Minecraft
- Versión del plugin
- Descripción detallada del problema
- Logs del servidor (si aplica)

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 👤 Autor

**Zymekoh**

- GitHub: [@tuusuario](https://github.com/tuusuario)

---

## ⭐ Dale una estrella

Si este plugin te ayudó, ¡considera darle una estrella al proyecto! ⭐

---

## 📊 Estado del Proyecto

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Minecraft Version](https://img.shields.io/badge/minecraft-1.21+-blue)
![Java Version](https://img.shields.io/badge/java-21-orange)
![License](https://img.shields.io/badge/license-MIT-green)

---

### 🔗 Links Útiles

- [Documentación de Floodgate](https://geysermc.org/floodgate/)
- [Documentación de Geyser](https://geysermc.org/)
- [Paper API Docs](https://docs.papermc.io/)

---

**¿Problemas con el plugin?** Únete a nuestro [Discord](#) o abre un [Issue](../../issues)
