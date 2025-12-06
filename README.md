[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/9TN0gSSC)

# Studify

Studify é uma aplicação Java com interface gráfica desenvolvida para auxiliar estudantes universitários na organização de sua rotina de estudos semestral. O sistema permite criar agendas personalizadas através da inserção de dados como disciplinas, prioridades, atividades com prazos e períodos disponíveis para estudo.

## Documentação

- **[Especificação do Projeto](docs/INF01120_grupo04_Etapa0.md)** - Descrição completa, objetivos e funcionamento
- **[Arquitetura MVC](docs/README_MVC.md)** - Documentação da estrutura de código e organização
- **[Diagrama UML](https://lucid.app/lucidchart/fd8c572a-eaba-4c25-a790-32c134a90f13/edit?view_items=x6O18qJcPzNJ&page=IYhW1RiEHMkl&invitationId=inv_ce44c2cb-6f27-4a37-bded-bc4249e2a028)** - Diagrama de classes completo

## Recursos e Interface

### Tecnologias Utilizadas
- **Java 25** - Linguagem principal de desenvolvimento
- **Swing** - Framework para interface gráfica
- **FlatLaf 3.5.4** - Look and Feel moderno 
- **FlatLaf Arc Orange Theme** - Tema visual com identidade laranja
- **LGoodDatePicker 11.2.1** - Seletor de datas
- **TinyLog 2.7.0** - Sistema de logging

### Interface Visual
- **Tema Arc Orange** aplicado consistentemente em todos os botões principais
- **Campos de texto destacados** com bordas cinza suaves para melhor visibilidade
- **Design responsivo** com layout otimizado para usabilidade

## Como Executar

### Opção 1: JAR Executável (Recomendado)
```bash
# Executar o arquivo JAR pré-compilado
java -jar dist/studify.jar

# Ou usar o Java 25 diretamente
"C:\Program Files\Java\jdk-25\bin\java.exe" -jar dist/studify.jar
```

### Opção 2: Compilação Manual
```bash
# Windows
.\compile.bat

# Executar após compilação
java -cp "build;lib/*" src.Studify
```

### Opção 3: Gerar Novo JAR
```bash
# Gerar JAR executável
.\build-jar.bat
```

## Testes

### Executar Todos os Testes
```bash
# Windows
.\run-tests.bat

# Linux/macOS  
./run-tests.sh
```

### Testes Individuais
```bash
java -cp "build;lib/*" src.test.TimeSlotEstudoTest
java -cp "build;lib/*" src.test.AlunoTest
java -cp "build;lib/*" src.test.AgendaEstudosTest
```

## Estrutura do Projeto

```
tcp-20252-final-grupo-04/
├── src/                     # Código fonte principal
│   ├── Studify.java        # Classe principal da aplicação
│   ├── model/              # Camada de dados e entidades
│   │   ├── entities/       # Entidades principais (Aluno, Disciplina, etc.)
│   │   ├── atividades/     # Tipos de atividades (Prova, Exercício, Trabalho)
│   │   ├── config/         # Configurações (DiaSemana, Impedimento, etc.)
│   │   └── allocation/     # Sistema de alocação de atividades
│   ├── controller/         # Camada de controle e lógica de negócio
│   │   ├── agenda/         # Geração de agendas
│   │   ├── atividades/     # Gerenciamento de atividades
│   │   └── comunicacao/    # Comunicação entre telas
│   ├── view/               # Interface gráfica (Swing)
│   │   ├── TelaInicial.java
│   │   ├── TelaRegistrarSemana.java
│   │   ├── TelaRegistrarTimeSlot.java
│   │   ├── TelaRegistrarAtividade.java
│   │   └── TelaAgenda.java
│   ├── utils/              # Utilitários (BinarySearchUtils)
│   └── test/               # Testes unitários (JUnit)
├── dist/                   # JAR executável
│   └── studify.jar        # Aplicação empacotada
├── build/                  # Arquivos compilados (.class)
├── lib/                    # Bibliotecas externas
├── docs/                   # Documentação do projeto
├── resources/              # Recursos e configurações
└── scripts de build        # compile.bat, build-jar.bat, run-tests.bat
```

## Sistema de Logging

O projeto utiliza a biblioteca **TinyLog 2.7.0** para registro de eventos e depuração.

### Configuração
- Arquivo de configuração: `resources/tinylog.properties`
- Níveis de log: INFO, WARN, ERROR
- Saída: Console durante execução

### Exemplos de Uso

**Log de inicialização:**
```java
Logger.info("Programa iniciado!");
```

**Log de erro de validação:**
```java
Logger.error("Formato de impedimento inválido: " + impedimento);
```

**Log de sucesso:**
```java
Logger.info("Atividades registradas com sucesso para o aluno.");
```

### Propósito
- Identificação de bugs durante desenvolvimento
- Rastreamento de fluxo da aplicação
- Validação de dados inseridos pelo usuário
- Alertas em casos de erro ou dados inválidos

## Desenvolvimento

### Configuração do Ambiente
- **JAVA_HOME** deve apontar para JDK 25
- Todas as dependências estão na pasta `lib/`
- Scripts de build automatizados para Windows

### Controle de Versão
- **Branch principal**: `mile-branch`
- **Repositório**: tcp-20252-final-grupo-04
- **Owner**: SW-Engineering-Courses-Karina-Kohl

### Arquitetura
O projeto segue o padrão **MVC (Model-View-Controller)**:
- **Model**: Entidades de dados e regras de negócio
- **View**: Interface gráfica em Swing com tema personalizado
- **Controller**: Lógica de aplicação e comunicação entre camadas

## Equipe

**Grupo 04 - INF01120**
- Cristopher dos Santos Filho
- Luís Filipe Moura (Facilitador)
- Milena Silva  
- Nickolas Xisto Machado
- Pedro Schuck de Azevedo

---

**Universidade Federal do Rio Grande do Sul (UFRGS)**  
**Técnicas de Construção de Programas - 2025/2**
