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
xcopy /s /e /Y build\* temp\

echo 4. Copiando recursos...
if exist resources xcopy /s /e /Y resources\* temp\
if exist com xcopy /s /e /Y com\* temp\com\

echo 5. Criando manifest...
if not exist temp\META-INF mkdir temp\META-INF
echo Main-Class: src.Studify > temp\META-INF\MANIFEST.MF
echo Class-Path: . >> temp\META-INF\MANIFEST.MF

echo 6. Criando JAR executavel...
cd temp
"%JAVA_HOME%\bin\jar.exe" -cfm ..\dist\studify.jar META-INF\MANIFEST.MF *
cd ..

echo 7. Criando script de execucao...
echo @echo off > dist\executar-studify.bat
echo echo Iniciando Studify com Java 25... >> dist\executar-studify.bat
echo echo. >> dist\executar-studify.bat
echo "C:\Program Files\Java\jdk-25\bin\java.exe" -jar "%%~dp0studify.jar" >> dist\executar-studify.bat
echo if errorlevel 1 ^( >> dist\executar-studify.bat
echo     echo. >> dist\executar-studify.bat
echo     echo ERRO: Nao foi possivel executar o Studify. >> dist\executar-studify.bat
echo     echo Verifique se o Java 25 esta instalado em: C:\Program Files\Java\jdk-25\ >> dist\executar-studify.bat
echo     echo. >> dist\executar-studify.bat
echo     pause >> dist\executar-studify.bat
echo ^) >> dist\executar-studify.bat

echo 8. Limpando arquivos temporarios...
rmdir /s /q temp

echo.
echo JAR criado com sucesso: dist\studify.jar
echo Script de execucao criado: dist\executar-studify.bat
echo.
echo Para executar fora da pasta do projeto:
echo   1. Copie studify.jar e executar-studify.bat para qualquer pasta
echo   2. Execute executar-studify.bat
echo.
pause