#!/bin/bash
# Script de compilação para projeto Studify
echo "Compilando projeto Studify..."

# Limpar build anterior
rm -rf build
mkdir -p build

# Compilar todos os arquivos Java
javac -cp ".:lib/*" -d build src/model/*.java src/model/atividades/*.java src/controller/*.java src/view/*.java src/test/*.java src/Studify.java

if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída com sucesso!"
    echo "📁 Arquivos .class em: build/"
    echo ""
    echo "Para executar:"
    echo "  java -cp \"build:lib/*\" src.Studify"
    echo "  java -cp \"build:lib/*\" src.test.TimeSlotEstudoTest"
else
    echo "❌ Erro na compilação!"
fi