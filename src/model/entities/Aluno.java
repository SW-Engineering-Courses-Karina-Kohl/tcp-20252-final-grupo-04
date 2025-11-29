package src.model.entities;
import src.controller.agenda.GeradorAgenda;
import src.controller.atividades.AtribuidorAtividades;
import src.model.atividades.Atividade;
import src.model.config.ConfiguracaoAgenda;
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

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }
    
    public void setAtribuidorAtividades(AtribuidorAtividades atribuidor) {
        if(atribuidor == null) {
            throw new IllegalArgumentException("Atribuidor não pode ser nulo");
        }
        this.atribuidor = atribuidor;
    }

    public void atribuirAtividadesAgenda() {
        if(this.atribuidor == null) {
            throw new IllegalStateException("Atribuidor de atividades não foi definido");
        }
        verificarDatasAtividadesEntreVigencia();
        this.atribuidor.atribuir(this.agenda, this.disciplinas);
    }

    public void verificarDatasAtividadesEntreVigencia()
    {
        for(Disciplina disciplina : this.disciplinas) 
        {
            for(Atividade atividade : disciplina.getAtividades()) 
            {
                if(!configuracaoAgenda.isDataEntreVigencia(atividade.getDataLimite()))
                {
                    throw new IllegalArgumentException("Atividade " + atividade.getNome() + " da disciplina " + disciplina.getNome() + " possui data limite fora do período de vigência da agenda.");
                }
            }
        }
    }

    public AgendaEstudos getAgenda() {
        return this.agenda;
    }

    public void setAgendaEstudos(AgendaEstudos agenda) {
        this.agenda = agenda;
    }

}
