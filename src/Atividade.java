package src;

import java.sql.Date;
import java.time.LocalDate;

public  abstract class Atividade {
    Disciplina disciplina;
    String nome;
    int prioridade;
    LocalDate dataLimite;

    public Atividade( String nome, int prioridade, LocalDate dataLimite, Disciplina disciplina) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.disciplina = disciplina;
    }
    
    public LocalDate getDataLimite() {
        return dataLimite;
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDisciplina(Disciplina disciplina) {
         this.disciplina = disciplina;
    }
    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public boolean checaDataLimiteEntreVigencia(ConfiguracaoAgenda confAgenda, LocalDate dataLimite){
        return true;
    }

    public abstract int getTotal();
    public abstract  double getPesoTipo();
    public abstract  double calculaPeso();
    public abstract double getPesoCalculado();
    public abstract void setPesoCalculado();
}
