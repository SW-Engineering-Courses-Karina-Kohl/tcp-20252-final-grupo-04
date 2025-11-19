# Estrutura MVC do Projeto Studify

O projeto foi reestruturado seguindo o padrão arquitetural **MVC (Model-View-Controller)** para melhor organização e separação de responsabilidades.

## 📁 Estrutura de Diretórios

```
src/
├── model/           # Camada de Modelo (Entidades e Dados)
├── view/            # Camada de Visão (Interface de Usuário)
├── controller/      # Camada de Controle (Lógica de Negócio)
├── test/            # Testes Unitários
├── util/            # Utilitários e Helpers
└── Studify.java     # Classe Principal
```

## 🏗️ Camadas do MVC

### 📊 **Model** (`src/model/`)
**Responsabilidade**: Representar dados e regras de negócio básicas

- `AgendaEstudos.java` - Agenda principal de estudos
- `AlocacaoAtividade.java` - Alocação de atividades em slots
- `Aluno.java` - Entidade representando um aluno
- `Atividade.java` - Classe abstrata para atividades
- `ConfiguracaoAgenda.java` - Configurações da agenda
- `DiaSemana.java` - Representação de dias da semana
- `Disciplina.java` - Entidade disciplina
- `Exercicio.java` - Tipo específico de atividade
- `Impedimento.java` - Restrições de horário
- `Prova.java` - Tipo específico de atividade
- `TimeSlotEstudo.java` - Slots de tempo para estudo
- `Trabalho.java` - Tipo específico de atividade

### 👀 **View** (`src/view/`)
**Responsabilidade**: Interface de usuário e interação

- `TelaInicial.java` - Tela principal da aplicação
- `TelaRegistrarSemana.java` - Interface para registro semanal

### 🎮 **Controller** (`src/controller/`)
**Responsabilidade**: Lógica de negócio e coordenação entre Model e View

- `AtribuidorAtividades.java` - Controla atribuição de atividades
- `CalculadoraPesoAtividades.java` - Calcula pesos das atividades
- `DistribuidorAtividades.java` - Distribui atividades na agenda
- `GeradorAgenda.java` - Gera agenda baseada nas configurações

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

- `src.model` - Classes do modelo
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

## 🔧 Compilação

Para compilar o projeto com a nova estrutura:

```bash
# Compilar todos os arquivos
javac -cp ".;lib/*" src/**/*.java

# Executar aplicação principal
java -cp ".;lib/*" src.Studify

# Executar testes
java -cp ".;lib/*" src.test.TimeSlotEstudoTest
```