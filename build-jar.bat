@echo off
echo Gerando JAR executavel do Studify...

REM Definir JAVA_HOME se não estiver definido
if "%JAVA_HOME%"=="" set JAVA_HOME=C:\Program Files\Java\jdk-25

REM Limpar builds anteriores
if exist dist rmdir /s /q dist
if exist temp rmdir /s /q temp
mkdir dist
mkdir temp

echo 1. Compilando o projeto...
call compile.bat
if errorlevel 1 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo 2. Extraindo dependencias...
cd temp
for %%f in (..\lib\*.jar) do (
    echo Extraindo %%f...
    "%JAVA_HOME%\bin\jar.exe" -xf "%%f"
)
cd ..

echo 3. Copiando arquivos compilados...
xcopy /s /e build\* temp\

echo 4. Criando manifest...
echo Main-Class: src.Studify > temp\META-INF\MANIFEST.MF
echo Class-Path: . >> temp\META-INF\MANIFEST.MF

echo 5. Criando JAR executavel...
cd temp
"%JAVA_HOME%\bin\jar.exe" -cfm ..\dist\studify.jar META-INF\MANIFEST.MF *
cd ..

echo 6. Limpando arquivos temporarios...
rmdir /s /q temp

echo.
echo JAR criado com sucesso: dist\studify.jar
echo.
echo Para executar:
echo   java -jar dist\studify.jar
echo   ou
echo   "%JAVA_HOME%\bin\java.exe" -jar dist\studify.jar
echo.
pause