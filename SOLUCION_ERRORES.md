# 🔧 SOLUCIÓN DE ERRORES - BDcpvper

## ✅ Correcciones Realizadas

He corregido los siguientes problemas:

### 1. **Consistencia de Paquetes**
- ✅ Todos los archivos Java ahora usan: `package com.zymekoh.bdcpvper;`
- ✅ El `plugin.yml` usa: `main: com.zymekoh.bdcpvper.Main`
- ✅ Todo en **minúsculas** para evitar problemas de capitalización

### 2. **Imports Correctos**
- ✅ `AnchorListener.java` - Agregado `import org.bukkit.inventory.ItemStack;`
- ✅ `CrystalListener.java` - Todos los imports correctos
- ✅ `Main.java` - Todos los imports correctos

### 3. **Estructura de Carpetas**
```
src/main/java/com/zymekoh/bdcpvper/
├── Main.java
├── CrystalListener.java
└── AnchorListener.java
```

## 🛠️ Si Persisten los Errores en tu IDE

### Eclipse:
1. Click derecho en el proyecto → **Refresh** (F5)
2. **Project** → **Clean...** → Selecciona tu proyecto → **Clean**
3. **Project** → **Build Project**

### IntelliJ IDEA:
1. **File** → **Invalidate Caches / Restart**
2. Click derecho en carpeta `src` → **Mark Directory as** → **Sources Root**
3. **Build** → **Rebuild Project**

### VS Code:
1. Cierra y vuelve a abrir VS Code
2. Presiona `Ctrl + Shift + P` → **Java: Clean Java Language Server Workspace**
3. Presiona `Ctrl + Shift + P` → **Java: Force Java Compilation**

## 🔍 Verificar Errores Específicos

Si ves errores rojos, verifica:

### Error: "Cannot resolve symbol FloodgateApi"
**Causa:** Gradle no ha descargado las dependencias
**Solución:**
```bash
gradlew.bat clean
gradlew.bat build --refresh-dependencies
```

### Error: "Package com.zymekoh.bdcpvper does not exist"
**Causa:** El IDE no reconoce la estructura del proyecto
**Solución:**
1. Asegúrate de que la carpeta `src/main/java` esté marcada como "Sources Root"
2. Recompila el proyecto
3. Reinicia el IDE

### Error en imports de Bukkit/Paper
**Causa:** Las dependencias no están descargadas
**Solución:**
```bash
gradlew.bat build
```
Esto descargará automáticamente Paper API y Floodgate API

## 📦 Compilación Limpia

Para asegurarte de que todo esté bien:

```bash
# 1. Limpiar todo
gradlew.bat clean

# 2. Recompilar desde cero
gradlew.bat build --refresh-dependencies

# 3. El JAR estará en:
# build/libs/BDcpvper-1.0.jar
```

## ✅ Verificación Final

Después de compilar, verifica que NO haya errores:

1. **Estructura correcta:**
   - ✅ `com/zymekoh/bdcpvper/` (todo minúsculas)
   - ✅ Tres archivos .java en la carpeta

2. **plugin.yml correcto:**
   - ✅ `main: com.zymekoh.bdcpvper.Main` (minúsculas)

3. **Compilación exitosa:**
   - ✅ Sin errores de compilación
   - ✅ JAR generado en `build/libs/`

## 🚨 Si TODAVÍA Hay Errores

Envíame una captura de pantalla del error específico que muestra tu IDE y te ayudaré a resolverlo.

Los errores más comunes son:
- Dependencias no descargadas (solución: `gradlew build`)
- Caché del IDE desactualizado (solución: limpiar caché)
- Carpeta src no marcada como fuente (solución: marcar como Sources Root)
