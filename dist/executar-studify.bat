@echo off
echo Iniciando Studify com Java 25...
echo.
"C:\Program Files\Java\jdk-25\bin\java.exe" -jar "%~dp0studify.jar"
if errorlevel 1 (
    echo.
    echo ERRO: Nao foi possivel executar o Studify.
    echo Verifique se o Java 25 esta instalado em: C:\Program Files\Java\jdk-25\
    echo.
    pause
)