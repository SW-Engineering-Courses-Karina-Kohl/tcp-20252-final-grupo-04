#!/bin/bash
# Script para executar testes JUnit
echo "Executando testes JUnit..."

# Compilar primeiro se necessário
if [ ! -d "build" ]; then
    echo "Compilando projeto primeiro..."
    ./compile.sh
fi

# Compilar testes separadamente
echo "Compilando testes..."
javac -cp "build:lib/*" -d build src/test/*.java

if [ $? -ne 0 ]; then
    echo "Erro na compilacao dos testes!"
    exit 1
fi

# Executar testes
echo ""
echo "Executando testes..."
echo ""

# Teste individual
echo "=== TimeSlotEstudoTest ==="
java -cp "build:lib/*" org.junit.runner.JUnitCore src.test.TimeSlotEstudoTest

echo ""
echo "=== AlunoTest ==="
java -cp "build:lib/*" org.junit.runner.JUnitCore src.test.AlunoTest

echo ""
if [ $? -eq 0 ]; then
    echo "Todos os testes concluidos!"
else
    echo "Alguns testes falharam!"
fi