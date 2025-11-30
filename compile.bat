@echo off
REM Script de compilacao para projeto Studify
echo Compilando projeto Studify...

REM Limpar build anterior
if exist build rmdir /s /q build
mkdir build

REM Compilar todos os arquivos Java
"C:\Program Files\Java\jdk-25\bin\javac.exe" -cp ".;lib/*" -d build src/model/entities/*.java src/model/atividades/*.java src/model/config/*.java src/model/allocation/*.java src/controller/agenda/*.java src/controller/atividades/*.java src/view/*.java src/Studify.java

if %errorlevel% equ 0 (
    echo Compilacao concluida com sucesso!
    echo Arquivos .class em: build/
    echo.
    echo Para executar:
    echo   java -cp "build;lib/*" src.Studify
    echo   echo "Testes requerem configuracao JUnit adequada"
) else (
    echo Erro na compilacao!
)

pause