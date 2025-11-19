package src.model.entities;
import src.model.atividades.Atividade;
import java.util.List;

public class Disciplina {
    
    private String nome;
    private double prioridade;
    private List<Atividade> atividades;
    private static int totalDisciplinas;

    Disciplina(String nome, double prioridade)
    {
        this.nome = nome;
        this.prioridade = prioridade;
        totalDisciplinas++;
    }

    public static int getTotalDisciplinas() {
        return totalDisciplinas;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setPrioridade(double prioridade) {
        this.prioridade = prioridade;
    }
    public double getPrioridade() {
        return prioridade;        
    }  
    
    public void adicionarAtividade(Atividade atividade) {
        atividades.add(atividade);
    }
    public List<Atividade> getAtividades() {
        return atividades;
    }

}
