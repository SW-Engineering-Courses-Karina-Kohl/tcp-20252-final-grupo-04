#!/bin/bash
# Script de compilacao para projeto Studify
echo "Compilando projeto Studify..."

# Limpar build anterior
rm -rf build
mkdir -p build

# Compilar todos os arquivos Java
javac -cp ".:lib/*" -d build src/model/entities/*.java src/model/atividades/*.java src/model/config/*.java src/model/allocation/*.java src/controller/agenda/*.java src/controller/atividades/*.java src/view/*.java src/test/*.java src/Studify.java

if [ $? -eq 0 ]; then
    echo "Compilacao concluida com sucesso!"
    echo "Arquivos .class em: build/"
    echo ""
    echo "Para executar:"
    echo "  java -cp \"build:lib/*\" src.Studify"
    echo "  java -cp \"build:lib/*\" src.test.TimeSlotEstudoTest"
else
    echo "Erro na compilacao!"
fi