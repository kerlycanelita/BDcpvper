@echo off
echo ========================================
echo    Compilando BDcpvper Plugin
echo ========================================
echo.

call gradlew.bat clean build

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo    Compilacion exitosa!
    echo ========================================
    echo.
    echo El archivo JAR esta en: build\libs\BDcpvper-1.0.jar
    echo.
) else (
    echo.
    echo ========================================
    echo    Error en la compilacion
    echo ========================================
    echo.
    echo Revisa los errores arriba.
)

pause
