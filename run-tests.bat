@echo off
REM Script para executar testes JUnit
echo Executando testes JUnit...

REM Compilar primeiro se necessário
if not exist build (
    echo Compilando projeto primeiro...
    call compile.bat
)

REM Compilar testes separadamente
echo Compilando testes...
"C:\Program Files\Java\jdk-25\bin\javac.exe" -cp "build;lib/*" -d build src/test/*.java

if %errorlevel% neq 0 (
    echo Erro na compilacao dos testes!
    pause
    exit /b 1
)

REM Executar testes
echo.
echo Executando testes...
echo.

REM Teste individual
echo === TimeSlotEstudoTest ===
"C:\Program Files\Java\jdk-25\bin\java.exe" -cp "build;lib/*" org.junit.runner.JUnitCore src.test.TimeSlotEstudoTest

echo.
if %errorlevel% equ 0 (
    echo Todos os testes concluidos!
) else (
    echo Alguns testes falharam!
)

pause