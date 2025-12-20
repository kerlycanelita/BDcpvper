# 📋 RESUMEN DEL PROYECTO BDcpvper

## ✅ Archivos Creados

### 📁 Estructura del Proyecto
```
plugins/
├── src/
│   └── main/
│       ├── java/com/zymekoh/bdcpvper/
│       │   ├── Main.java              # Clase principal del plugin
│       │   ├── CrystalListener.java   # Listener para cristales del End
│       │   └── AnchorListener.java    # Listener para Respawn Anchors
│       └── resources/
│           ├── plugin.yml             # Configuración del plugin
│           └── config.yml             # Configuración personalizable
├── build.gradle.kts                   # Configuración de Gradle
├── settings.gradle.kts                # Configuración del proyecto
├── build.bat                          # Script de compilación para Windows
├── .gitignore                         # Archivos ignorados por Git
└── README.md                          # Documentación completa
```

## 🎯 Funcionalidades Implementadas

### 1️⃣ Detección de Jugadores Bedrock
- ✅ Usa Floodgate API para identificar jugadores de Bedrock
- ✅ Verifica automáticamente si Floodgate está instalado
- ✅ Desactiva el plugin si Floodgate no está disponible

### 2️⃣ Cristales del End (End Crystals)
- ✅ Colocación instantánea sin delay
- ✅ Destrucción instantánea al golpearlos
- ✅ Genera explosión correctamente (6.0 de potencia)
- ✅ No rompe bloques ni causa fuego
- ✅ Solo afecta a jugadores Bedrock

### 3️⃣ Respawn Anchors
- ✅ Colocación sin delay
- ✅ Destrucción instantánea
- ✅ Drop automático del item al romperlo
- ✅ Solo afecta a jugadores Bedrock

### 4️⃣ Eventos Implementados
- ✅ `PlayerInteractEvent` - Para colocación de cristales
- ✅ `EntityDamageByEntityEvent` - Para destrucción de cristales
- ✅ `BlockPlaceEvent` - Para colocación de anchors
- ✅ `BlockBreakEvent` - Para destrucción de anchors

## 🔧 Dependencias

### Repositorios Configurados:
- Maven Central
- Paper MC Repository
- GeyserMC OpenCollab (Floodgate)

### Dependencias:
- **Paper API 1.21.1** (compileOnly)
- **Floodgate API 2.2.3** (compileOnly)
- **Shadow Plugin 8.1.1** (para empaquetado)

## 📝 Archivos de Configuración

### plugin.yml
```yaml
name: BDcpvper
version: 1.0
main: com.zymekoh.bdcpvper.Main
api-version: 1.21
author: Zymekoh
depend: [floodgate]
```

### config.yml (Personalizable)
- Habilitar/deshabilitar funcionalidades
- Configurar potencia de explosiones
- Mensajes personalizables
- Modo debug

## 🚀 Cómo Compilar

### Opción 1: Script Automático (Windows)
```bash
build.bat
```

### Opción 2: Gradle Manual
```bash
# Windows
gradlew.bat build

# Linux/Mac
./gradlew build
```

### Ubicación del JAR Compilado:
```
build/libs/BDcpvper-1.0.jar
```

## 📦 Instalación en el Servidor

1. Asegúrate de tener instalado:
   - PaperMC 1.21.1+
   - Geyser
   - Floodgate

2. Copia `BDcpvper-1.0.jar` a la carpeta `plugins/` del servidor

3. Reinicia el servidor

4. Verifica en los logs:
```
[BDcpvper] ============================================
[BDcpvper] BDcpvper activado correctamente!
[BDcpvper] Jugadores Bedrock pueden usar:
[BDcpvper] - Cristales del End sin delay
[BDcpvper] - Respawn Anchors sin delay
[BDcpvper] ============================================
```

## 🎮 Comportamiento del Plugin

### Para Jugadores Bedrock:
- Los cristales del End se colocan y rompen al instante
- Los Respawn Anchors se colocan y rompen al instante
- Las explosiones de cristales funcionan correctamente
- Los items dropean normalmente

### Para Jugadores Java:
- No se ven afectados
- Comportamiento normal de Minecraft

## ⚙️ Características Técnicas

- **Lenguaje**: Java 21
- **API**: Paper/Spigot 1.21+
- **Sistema de Eventos**: Bukkit Event System
- **Prioridad de Eventos**: HIGHEST (para asegurar ejecución)
- **Integración**: Floodgate API 2.2.3

## 🐛 Solución de Problemas

### El plugin no se activa:
1. Verifica que Floodgate esté instalado
2. Revisa la versión de Paper (debe ser 1.21.1+)
3. Revisa los logs del servidor

### Los jugadores Bedrock no tienen funcionalidad:
1. Verifica que Geyser funcione correctamente
2. Confirma que Floodgate detecta a los jugadores
3. Revisa los permisos (por defecto: todos)

### Error de compilación:
1. Asegúrate de tener Java 21 instalado
2. Ejecuta `gradlew clean` antes de compilar
3. Verifica tu conexión a internet (descarga dependencias)

## 📄 Licencia

Código abierto - Modificable según tus necesidades

## 👤 Autor

**Zymekoh**

---

✅ **PROYECTO COMPLETO Y LISTO PARA COMPILAR**
