package src;
import src.AgendaEstudos;
import src.ConfiguracaoAgenda;
import src.Disciplina;
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
        this.agenda = new AgendaEstudos();
    }
    public Aluno() { 
        this.configuracaoAgenda = new ConfiguracaoAgenda();
        this.disciplinas = new ArrayList<>();
        this.agenda = new AgendaEstudos();
    };

    // Getters e Setters

    public List<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public ConfiguracaoAgenda getConfiguracaoAgenda() {
        return this.configuracaoAgenda;
    }

    public void setConfiguracaoAgenda(ConfiguracaoAgenda configuracaoAgenda) {
        this.configuracaoAgenda = configuracaoAgenda;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public AgendaEstudos getAgenda() {
        return this.agenda;
    }

    public void setAgendaEstudos(AgendaEstudos agenda) {
        this.agenda = agenda;
    }

}
