# Estrutura MVC do Projeto Studify

O projeto foi reestruturado seguindo o padrão arquitetural **MVC (Model-View-Controller)** para melhor organização e separação de responsabilidades.

## 📁 Estrutura de Diretórios

```
src/
├── model/           # Camada de Modelo (Entidades e Dados)
│   └── atividades/  # Tipos de Atividades
├── view/            # Camada de Visão (Interface de Usuário)
├── controller/      # Camada de Controle (Lógica de Negócio)
├── test/            # Testes Unitários
├── util/            # Utilitários e Helpers
└── Studify.java     # Classe Principal
```

## 🏗️ Camadas do MVC

### 📊 **Model** (`src/model/`)
**Responsabilidade**: Representar dados e regras de negócio básicas

#### Entidades Principais:
- `AgendaEstudos.java` - Agenda principal de estudos
- `AlocacaoAtividade.java` - Alocação de atividades em slots
- `Aluno.java` - Entidade representando um aluno
- `ConfiguracaoAgenda.java` - Configurações da agenda
- `DiaSemana.java` - Representação de dias da semana
- `Disciplina.java` - Entidade disciplina
- `Impedimento.java` - Restrições de horário
- `TimeSlotEstudo.java` - Slots de tempo para estudo

#### Atividades (`src/model/atividades/`):
- `Atividade.java` - Classe abstrata base para atividades
- `Exercicio.java` - Tipo específico de atividade
- `Prova.java` - Tipo específico de atividade
- `Trabalho.java` - Tipo específico de atividade

### 👀 **View** (`src/view/`)
**Responsabilidade**: Interface de usuário e interação

- `TelaInicial.java` - Tela principal da aplicação
- `TelaRegistrarSemana.java` - Interface para registro semanal

### 🎮 **Controller** (`src/controller/`)
**Responsabilidade**: Lógica de negócio e coordenação entre Model e View

#### 📅 **Agenda** (`src/controller/agenda/`)
- `GeradorAgenda.java` - Gera agenda baseada nas configurações

#### 📚 **Atividades** (`src/controller/atividades/`)
- `AtribuidorAtividades.java` - Controla atribuição de atividades
- `CalculadoraPesoAtividades.java` - Calcula pesos das atividades
- `DistribuidorAtividades.java` - Distribui atividades na agenda

### 🧪 **Test** (`src/test/`)
**Responsabilidade**: Testes unitários

- `ConfiguracaoAgendaTest.java` - Testes para ConfiguracaoAgenda
- `TimeSlotEstudoTest.java` - Testes para TimeSlotEstudo

## 🔄 Fluxo de Dados

```
View → Controller → Model
  ↑                   ↓
  ←------- Controller ←
```

1. **View** recebe entrada do usuário
2. **Controller** processa a lógica de negócio
3. **Model** manipula e armazena dados
4. **Controller** retorna resultado para **View**

## 📦 Packages

- `src.model` - Classes do modelo principal
- `src.model.atividades` - Hierarquia de atividades (Atividade, Prova, Exercicio, Trabalho)
- `src.view` - Classes de interface
- `src.controller` - Classes de controle
- `src.test` - Classes de teste

## 🚀 Benefícios da Estrutura MVC

✅ **Separação de Responsabilidades**: Cada camada tem função específica
✅ **Manutenibilidade**: Fácil localização e modificação de código
✅ **Testabilidade**: Testes isolados por camada
✅ **Reutilização**: Controllers e Models reutilizáveis
✅ **Escalabilidade**: Fácil adição de novas funcionalidades
✅ **Organização**: Código bem estruturado e legível

## 🔧 Compilação e Execução

### Método Recomendado (com build/ separado):

**Windows:**
```bash
# Compilar projeto
.\compile.bat

# Executar aplicação principal
java -cp "build;lib/*" src.Studify

# Executar testes
java -cp "build;lib/*" src.test.TimeSlotEstudoTest
```

**Linux/macOS:**
```bash
# Compilar projeto
chmod +x compile.sh
./compile.sh

# Executar aplicação principal
java -cp "build:lib/*" src.Studify

# Executar testes
java -cp "build:lib/*" src.test.TimeSlotEstudoTest
```

### Método Manual:
```bash
# Compilar todos os arquivos
javac -cp ".;lib/*" -d build src/**/*.java src/**/**/*.java

# Executar aplicação principal
java -cp "build;lib/*" src.Studify

# Executar testes
java -cp "build;lib/*" src.test.TimeSlotEstudoTest
```

## 📁 Organização de Arquivos

- **Código fonte**: `src/` (apenas arquivos .java)
- **Arquivos compilados**: `build/` (arquivos .class organizados)
- **Bibliotecas**: `lib/` (se houver dependências externas)
- **Configuração VS Code**: `.vscode/settings.json` (oculta .class no explorer)