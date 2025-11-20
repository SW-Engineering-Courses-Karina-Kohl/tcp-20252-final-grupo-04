@echo off
REM Script de compilação para projeto Studify
echo Compilando projeto Studify...

REM Limpar build anterior
if exist build rmdir /s /q build
mkdir build

REM Compilar todos os arquivos Java
"C:\Program Files\Java\jdk-25\bin\javac.exe" -cp ".;lib/*" -d build src/model/entities/*.java src/model/atividades/*.java src/model/config/*.java src/model/allocation/*.java src/controller/agenda/*.java src/controller/atividades/*.java src/view/*.java src/test/*.java src/Studify.java

if %errorlevel% equ 0 (
    echo ✅ Compilação concluída com sucesso!
    echo 📁 Arquivos .class em: build/
    echo.
    echo Para executar:
    echo   java -cp "build;lib/*" src.Studify
    echo   java -cp "build;lib/*" src.test.TimeSlotEstudoTest
) else (
    echo ❌ Erro na compilação!
)

pause