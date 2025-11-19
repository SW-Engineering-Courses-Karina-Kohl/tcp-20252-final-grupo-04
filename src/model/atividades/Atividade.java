package src.model.atividades;
import src.model.entities.Disciplina;
import java.time.LocalDateTime;

public  abstract class Atividade {
    Disciplina disciplina;
    String nome;
    int prioridade;
    LocalDate dataLimite;

    public Atividade( String nome, int prioridade, LocalDate dataLimite) {
        this.nome = nome;
        this.dataLimite = dataLimite;
        this.disciplina = disciplina;
        this.pesoCalculado = 0;
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
    
    public double getPesoCalculado() {
        return pesoCalculado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public  Disciplina getDisciplina(){

        return disciplina;
    }


    public void setDisciplina(Disciplina disciplina) {
         this.disciplina = disciplina;
    }
    public void setDataLimite(LocalDateTime dataLimite) {
        this.dataLimite = dataLimite;
    }
    public  void setPesoCalculado(double pesoCalculado){
        this.pesoCalculado = pesoCalculado;
    }

    //public boolean checaDataLimiteEntreVigencia(ConfiguracaoAgenda confAgenda, LocalDate dataLimite){
    //    return true;
    //}

    public abstract int getTotal();
    public abstract  double getPesoTipo();
    public abstract  double calculaPeso();
    public abstract double getPesoCalculado();
    public abstract Disciplina getDisciplina();
}
