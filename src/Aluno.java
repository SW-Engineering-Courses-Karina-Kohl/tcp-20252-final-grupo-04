import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private List<Disciplina> disciplinas;
    private AgendaEstudos agenda;
    private ConfiguracaoAgenda configuracaoAgenda;

    // Construtores

    public Aluno(ConfiguracaoAgenda configuracaoAgenda) {
        this.configuracaoAgenda = configuracaoAgenda;
        this.disciplinas = new ArrayList<>();
        this.agenda = new AgendaEstudos(configuracaoAgenda);
    }
    public Aluno() { 
        this.configuracaoAgenda = new ConfiguracaoAgenda();
        this.disciplinas = new ArrayList<>();
        this.agenda = new AgendaEstudos(configuracaoAgenda);
    };

    // Getters e Setters

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public ConfiguracaoAgenda getConfiguracaoAgenda() {
        return configuracaoAgenda;
    }

    public void setConfiguracaoAgenda(ConfiguracaoAgenda configuracaoAgenda) {
        this.configuracaoAgenda = configuracaoAgenda;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public AgendaEstudos getAgenda() {
        return agenda;
    }

    public void setAgendaEstudos(AgendaEstudos agenda) {
        this.agenda = agenda;
    }

}
