[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/9TN0gSSC)
# Studify
Studify é uma aplicação voltada para ajudar a rotina de estudos semestral de um estudante universitário. Para utilizá-lo e gerar sua própria agenda de estudos personalizada, basta inserir dados como disciplinas, ordem de prioridade destas, tarefas e suas datas de entrega e períodos tipicamente livres em uma semana.

## 📚 Documentação

- **[Especificação do Projeto](docs/INF01120_grupo04_Etapa0.md)** - Descrição completa, objetivos e funcionamento
- **[Arquitetura MVC](docs/README_MVC.md)** - Documentação da estrutura de código e organização
- **[Diagrama UML](https://lucid.app/lucidchart/fd8c572a-eaba-4c25-a790-32c134a90f13/edit?view_items=x6O18qJcPzNJ&page=IYhW1RiEHMkl&invitationId=inv_ce44c2cb-6f27-4a37-bded-bc4249e2a028)** - Diagrama de classes completo

## 🚀 Como Executar

### Compilação Rápida:
```bash
# Windows
.\compile.bat

# Linux/macOS  
./compile.sh
```

### Execução:
```bash
# Aplicação principal
java -cp "build;lib/*" src.Studify

# Testes
java -cp "build;lib/*" src.test.TimeSlotEstudoTest
```

## 🏗️ Estrutura do Projeto

```
├── src/                    # Código fonte
│   ├── model/             # Entidades e dados
│   │   └── atividades/    # Tipos de atividades (Prova, Exercício, Trabalho)
│   ├── controller/        # Lógica de negócio
│   ├── view/             # Interface de usuário  
│   └── test/             # Testes unitários
├── docs/                  # Documentação
├── build/                 # Arquivos compilados (.class)
└── lib/                   # Bibliotecas externas
```

## 👥 Equipe

**Grupo 04 - INF01120**
- Cristopher dos Santos Filho
- Luís Filipe Moura (Facilitador)
- Milena Silva  
- Nickolas Xisto Machado
- Pedro Schuck de Azevedo 

# Logging do sistema
Para produzir logging conforme a aplicação é executada, foi empregada a biblioeca tinylog cuja versão é 2.7.

Para acessar a documentação oficial, basta clicar no seguinte link: https://tinylog.org/logging/

Exemplos de uso: Na classe TelaInicial, linha Y é usado logging através do comando Logger.info para avisar ao usuário no terminal que a aplicação foi iniciada.
Na classe TelaRegistrarSemana, linha Z é usado logging por meio de Logger.error para avisar ao usuário no terminal que o impedimento escrito está em um formato inválido.

Propósito: Foi feito uso de logging com a finalidade de identificar bugs, gerar alertas em casos de erro e _.
